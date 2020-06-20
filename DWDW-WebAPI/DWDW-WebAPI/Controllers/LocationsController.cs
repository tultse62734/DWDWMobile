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
using DWDW_WebAPI.ViewModel;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/Locations")]
    public class LocationsController : ApiController
    {
        //GET ALL Location for admin
        [Authorize(Roles = "1")]
        [HttpGet]
        [Route("api/admin/locationList")]
        public IHttpActionResult GetLocations()
        {
            var locationList = db.Locations.ToList();
            return Ok(locationList);
        }

        //Search Location for admin
        [Authorize(Roles = "1")]
        [HttpGet]
        [Route("api/admin/locationFinder/{locationID}")]
        public IHttpActionResult FindLocations(int locationID)
        {
            var searchedLocation = db.Locations.Find(locationID);
            return Ok(searchedLocation);
        }

        //Get assigned Location for manager and worker
        [Authorize(Roles = "2, 3")]
        [HttpGet]
        [Route("api/subaccount/locationList")]
        public IHttpActionResult GetAssignedLocations()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountID = Convert.ToInt32(ID);
            var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == accountID)).ToList();
            return Ok(locationList);
        }

        //Search Assigned Location for manager and worker
        [Authorize(Roles = "2,3")]
        [HttpGet]
        [Route("api/subaccount/locationFinder/{locationID}")]
        public IHttpActionResult FindAssignedLocations(int locationID)
        {
            var identity = (ClaimsIdentity)User.Identity;
            var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountID = Convert.ToInt32(ID);
            var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == accountID)).ToList();
            var searchLocation = locationList.FirstOrDefault(x => x.locationId == locationID);
            return Ok(searchLocation);
        }
        [Route("{id}/rooms")]
        // GET: api/Locations/5
        [ResponseType(typeof(Location))]
        public IHttpActionResult GetRoomInLocation(int id)
        {
            IQueryable<Location> location = db.Locations.Where(l => l.locationId == id);
            if (!location.Any())
            {
                return NotFound();
            }
            var rooms = db.Rooms.Where(r => r.locationId == id)
                .Select(r => new RoomViewModel()
                {
                    roomId = r.roomId,
                    roomCode = r.roomCode,
                    locationId = r.locationId,
                    isActive = r.isActive
                }).ToList();
            if (!rooms.Any())
            {
                return NotFound();
            }
            return Ok(rooms);
        }
        [Route("{id}/devices")]
        // GET: api/Locations/5
        [ResponseType(typeof(Location))]
        public IHttpActionResult GetDeviceInLocation(int id)
        {
            IQueryable<Location> location = db.Locations.Where(l => l.locationId == id);
            if (!location.Any())
            {
                return NotFound();
            }
            var rooms = db.Rooms.Where(r => r.locationId == id)
                .Select(r => new RoomViewModel()
                {
                    roomId = r.roomId,
                    roomCode = r.roomCode,
                    locationId = r.locationId,
                    isActive = r.isActive
                }).ToList();
            var devices = from d in db.Devices
                         join rd in db.RoomDevices on d.deviceId equals rd.deviceId
                         join r in db.Rooms on rd.roomId equals r.roomId
                         where r.locationId == id
                         select new
                         {
                             d.deviceId,
                             d.deviceCode,
                             d.deviceStatus,
                             d.isActive
                         };
            if (!devices.Any())
            {
                return NotFound();
            }
            return Ok(devices);
        }

        // PUT: api/Locations/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutLocation(int id, Location location)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != location.locationId)
            {
                return BadRequest();
            }

            db.Entry(location).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LocationExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Locations
        [ResponseType(typeof(Location))]
        public IHttpActionResult PostLocation(Location location)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Locations.Add(location);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = location.locationId }, location);
        }

        // DELETE: api/Locations/5
        [ResponseType(typeof(Location))]
        public IHttpActionResult DeleteLocation(int id)
        {
            Location location = db.Locations.Find(id);
            if (location == null)
            {
                return NotFound();
            }

            db.Locations.Remove(location);
            db.SaveChanges();

            return Ok(location);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool LocationExists(int id)
        {
            return db.Locations.Count(e => e.locationId == id) > 0;
        }
    }
}