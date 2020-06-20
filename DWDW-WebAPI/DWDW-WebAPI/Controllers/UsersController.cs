using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/Users")]
    public class UsersController : ApiController
    {
        public UsersController()
        {
            db.Configuration.ProxyCreationEnabled = false;
        }
        private DWDBContext db = new DWDBContext();


        // GET: api/Users
        [Route("")]
        public IQueryable<User> GetUsers()
        {
            return db.Users;
        }

        // GET: api/Users/5
        [Route("{id}")]
        [ResponseType(typeof(User))]
        public IHttpActionResult GetUser(int id, int roleId = 0)
        {
            User user = db.Users.Find(id);

            if (user == null)
            {
                return NotFound();
            }

            return Ok(user);
        }
        [Route("manager")]
        [ResponseType(typeof(User))]
        public IHttpActionResult GetManager()
        {
            IQueryable<User> managers = db.Users.Where(u => u.roleId == 2);

            if (managers == null)
            {
                return NotFound();
            }
            var result = managers.Select(manager => new UserViewModel()
            {
                userId = manager.userId,
                userName = manager.userName,
                password = manager.password,
                phone = manager.phone,
                dateOfBirth = manager.dateOfBirth,
                gender = manager.gender,
                deviceToken = manager.deviceToken,
                roleId = manager.roleId,
                isActive = manager.isActive
            }).ToList();

            return Ok(result);
        }

        [Route("managers/{id}/locations")]
        [ResponseType(typeof(User))]
        public IHttpActionResult GetUserWithLocation(int id)
        {
            IQueryable<User> user = db.Users.Where(u => u.userId == id && u.roleId == 2);
            if (!user.Any())
            {
                return NotFound();
            }
            var location = from l in db.Locations
                        join ul in db.UserLocations on l.locationId equals ul.locationId
                        where ul.userId == id
                        select new
                        {
                            l.locationId,
                            l.locationCode,
                            l.isActive
                        };
            if (!location.Any())
            {
                return Ok("Location Empty!");
            }
            return Ok(location);
        }

        [Route("managers/locations")]
        [ResponseType(typeof(User))]
        public IHttpActionResult GetManagerLocation()
        {
            IQueryable<User> managers = db.Users.Where(u => u.roleId == 2);

            if (managers == null)
            {
                return NotFound();
            }
            var listManagerWithLocation = from m in managers
                                  join ml in db.UserLocations on m.userId equals ml.userId
                                  join l in db.Locations on ml.locationId equals l.locationId
                                  select new
                                  {
                                      m.userId,
                                      m.userName,
                                      m.password,
                                      m.phone,
                                      m.dateOfBirth,
                                      m.gender,
                                      m.deviceToken,
                                      m.roleId,
                                      m.isActive,
                                      Location = new LocationViewModel()
                                      {
                                          locationId = l.locationId,
                                          locationCode = l.locationCode,
                                          isActive = l.isActive
                                      }
                                  };
            
            return Ok(listManagerWithLocation);
        }

        // PUT: api/Users/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUser(int id, User user)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != user.userId)
            {
                return BadRequest();
            }

            db.Entry(user).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UserExists(id))
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

        // POST: api/Users
        [ResponseType(typeof(User))]
        public IHttpActionResult PostUser(User user)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Users.Add(user);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = user.userId }, user);
        }

        // DELETE: api/Users/5
        [ResponseType(typeof(User))]
        public IHttpActionResult DeleteUser(int id)
        {
            User user = db.Users.Find(id);
            if (user == null)
            {
                return NotFound();
            }

            db.Users.Remove(user);
            db.SaveChanges();

            return Ok(user);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool UserExists(int id)
        {
            return db.Users.Count(e => e.userId == id) > 0;
        }
    }
}