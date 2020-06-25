using DWDW_WebAPI.Models;
using DWDW_WebAPI.Services;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web.Http;
using System.Web.Http.Description;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/api/Locations")]
    public class LocationsController : ApiController
    {
        private ILocationService locationService;
        private ModelMapping modelMapping;

        public LocationsController()
        {
            this.locationService = new LocationService(new DWDBContext());
            this.modelMapping = new ModelMapping();
        }

        //GET ALL Location for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("")]
        [ResponseType(typeof(LocationViewModel))]
        public IHttpActionResult GetLocations()
        {
            try
            {
                var list = locationService.GetLocations();
                if (!list.Any())
                {
                    return NotFound();
                }
                else
                {
                    return Ok(list);
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        //Get Location by Id
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("{locationId}")]
        [ResponseType(typeof(LocationViewModel))]
        public IHttpActionResult GetLocation(int locationId)
        {
            var location = locationService.GetLocationById(locationId);
            LocationViewModel viewModel = modelMapping.GetLocationMapping(location);
            return Ok(viewModel);
        }
        #region hoang
        //Get assigned Location for manager and worker
        //[Authorize(Roles = Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        //[HttpGet]
        //[Route("assigned")]
        //[ResponseType(typeof(LocationViewModel))]
        //public IHttpActionResult GetAssignedLocations()
        //{
        //    var identity = (ClaimsIdentity)User.Identity;
        //    var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
        //    int accountId = Convert.ToInt32(Id);
        //    var locationList = locationService.GetAssignedLocations(accountId);
        //    return Ok(locationList);
        //}

        //Search Assigned Location for manager and worker
        //[Authorize(Roles = Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        //[HttpGet]
        //[Route("assigned/{locationId}")]
        //[ResponseType(typeof(LocationViewModel))]
        //public IHttpActionResult GetAssignedLocation(int locationId)
        //{
        //    var identity = (ClaimsIdentity)User.Identity;
        //    var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
        //    int accountId = Convert.ToInt32(ID);
        //    var locationList = locationService.GetAssignedLocations(accountId);
        //    var searchLocation = locationService.GetLocationById(locationId);
        //    return Ok(searchLocation);
        //}
        #endregion hoang

        
        [HttpGet]
        [Route("assigned")]
        [ResponseType(typeof(LocationViewModel))]
        public IHttpActionResult GetAssignedLocations(int userId)
        {
            var locations = locationService.GetAssignedLocations(userId);
            return Ok(locations);
        }

        // PUT: api/Locations/5
        [HttpPut]
        [Route("")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutLocation(int id, LocationViewModel locationViewModel)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                if (locationViewModel.locationId != id) return BadRequest();
                Location location = locationService.GetLocationById(id);
                if (location == null) return NotFound();
                //mapping
                modelMapping.UpdateLocationMapping(locationViewModel, location);
                if (locationService.UpdateLocation(location))
                {
                    return Ok("Update succeed.");
                }
                else
                {
                    return BadRequest("Can not update Location.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {

                return InternalServerError();
            }
        }

        // POST: api/Locations
        [HttpPost]
        [Route("")]
        [ResponseType(typeof(Location))]
        public IHttpActionResult PostLocation(LocationViewModel locationViewModel)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                //mapping
                Location location = modelMapping.CreateLocationMapping(locationViewModel);
                if (locationService.InsertLocation(location))
                {
                    return Ok("Insert succeed.");
                }
                else
                {
                    return BadRequest("Can not insert Location.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {
                if (locationService.LocationExists(locationViewModel.locationId))
                {
                    return Conflict();
                }
                return InternalServerError();
            }
        }

        [HttpPut]
        [ResponseType(typeof(void))]
        [Route("{locationId}/deactive")]
        public IHttpActionResult PutLocationDeactive(int locationId)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                Location location = locationService.GetLocationById(locationId);
                if (location == null) return NotFound();
                if (location.isActive.Equals(false))
                {
                    return BadRequest("Location already deactivated.");
                }
                if (locationService.DeactiveLocation(location))
                {
                    return Ok("Deactive succeed.");
                }
                else
                {
                    return BadRequest("Can not deactive Location.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {
                return InternalServerError();
            }
        }

    }
}