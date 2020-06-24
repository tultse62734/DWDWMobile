using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Claims;
using System.Web.Http;
using System.Web.Http.Description;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.Services;
using DWDW_WebAPI.ViewModel;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/api/Locations")]
    public class LocationsController : ApiController
    {
        private ILocationService locationService;

        public LocationsController()
        {
            this.locationService = new LocationService(new DWDBContext());
        }
        public LocationsController(ILocationService locationService)
        {
            this.locationService = locationService;
        }
        //GET ALL Location for admin
        //[Authorize(Roles = "1")]
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

        //Search Location for admin
        //[Authorize(Roles = "1")]
        [HttpGet]
        [Route("{locationId}")]
        [ResponseType(typeof(LocationViewModel))]
        public IHttpActionResult GetLocations(int locationId)
        {
            var searchedLocation = locationService.GetLocationById(locationId);
            return Ok(searchedLocation);
        }

        //Get assigned Location for manager and worker
        //[Authorize(Roles = "2, 3")]
        [HttpGet]
        [Route("assigned")]
        [ResponseType(typeof(LocationViewModel))]
        public IHttpActionResult GetAssignedLocations()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(Id);
            var locationList = locationService.GetAssignedLocations(accountId);
            return Ok(locationList);
        }

        //Search Assigned Location for manager and worker
        //[Authorize(Roles = "2,3")]
        [HttpGet]
        [Route("assigned/{locationId}")]
        [ResponseType(typeof(LocationViewModel))]
        public IHttpActionResult GetAssignedLocations(int locationId)
        {
            var identity = (ClaimsIdentity)User.Identity;
            var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(ID);
            var locationList = locationService.GetAssignedLocations(accountId);
            var searchLocation = locationService.GetLocationById(locationId);
            return Ok(searchLocation);
        }

        // PUT: api/Locations/5
        [ResponseType(typeof(void))]
        [Route("")]
        public IHttpActionResult PutLocation(int id,Location location)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != location.locationId)
            {
                return NotFound();
            }

            locationService.UpdateLocation(location);

            try
            {
                locationService.Save();
                return Ok("Update succeed.");
            }
            catch (DbUpdateConcurrencyException)
            {
                return StatusCode(HttpStatusCode.MethodNotAllowed);
            }
        }

        // POST: api/Locations
        [Route("")]
        [ResponseType(typeof(Location))]
        public IHttpActionResult PostLocation(Location location)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            locationService.InsertLocation(location);
            try
            {
                locationService.Save();
                return CreatedAtRoute("DefaultApi", new { id = location.locationId }, location);
            }
            catch (Exception)
            {
                if (locationService.LocationExists(location.locationId))
                {
                    return Conflict();
                }
                return BadRequest("Insert failed.");
            }
            
        }

        //DELETE: api/Locations/5
        [Route("")]
        [ResponseType(typeof(Location))]
        public IHttpActionResult DeleteLocation(int locationId)
        {
            var location = locationService.GetLocationById(locationId);
            if (location == null)
            {
                return NotFound();
            }
            locationService.DeactiveLocation(location);
            try
            {
                locationService.Save();
                return Ok("Deactive succeed!");
            }
            catch (Exception)
            {
                return StatusCode(HttpStatusCode.MethodNotAllowed);
            }
        }
    }
}