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

namespace DWDW_WebAPI.Controllers
{
    public class UsersController : ApiController
    {
        private DWDBContext db = new DWDBContext();

        // GET ALL User for admin
        [Authorize(Roles = "1")]
        [HttpGet]
        [Route("api/admin/userList")]
        public IHttpActionResult GetUsers()
        {
            var userList = db.Users.ToList();
            return Ok(userList);
        }

        //Get user detail
        [Authorize(Roles = "1, 2, 3")]
        [HttpGet]
        [Route("api/user/UserDetail")]
        public IHttpActionResult getAccountDetail()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            var currentAccount = db.Users.Find(int.Parse(ID));
            return Ok(currentAccount);
        }

        //Search User for admin
        [Authorize(Roles = "1")]
        [HttpGet]
        [Route("api/admin/userFinder/{userID}")]
        public IHttpActionResult FindUser(int userID)
        {
            var searchedUser = db.Users.Find(userID);
            return Ok(searchedUser);
        }

        //Get ALL assigned worker for manager


        //Search assigned worker for manager
        //[Authorize(Roles = "2")]
        //[HttpGet]
        //[Route("api/manager/userFinder/{userID}")]
        //public IHttpActionResult FindUserForManager(int userID)
        //{
        //    //Lấy ID của manager account đang đăng nhập
        //    var identity = (ClaimsIdentity)User.Identity;
        //    var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;

        //    //Chọn ra location đang gán với manager đó
        //    var relatedLocation = db.UserLocations.Where(c => c.userId == int.Parse(ID)).ToList();

        //    var searchedUser = relatedLocation.Where(x => x.userId == userID).FirstOrDefault();
        //    return Ok(searchedUser);
        //}



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