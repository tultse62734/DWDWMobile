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
using System.Web.Script.Serialization;
using System.Web.Http.Description;
using DWDW_WebAPI.Models;
using System.Text;
using DWDW_WebAPI.Firebase;
using DWDW_WebAPI.ViewModel;
using DWDW_WebAPI.Contants;

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

        // GET ALL User for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("admin/Users")]
        public IHttpActionResult GetUsers()
        {
            var userList = db.Users.ToList();
            return Ok(userList);
        }

        //Get user detail
        [Authorize(Roles = Constant.ADMIN_ROLE + "," + Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        [HttpGet]
        [Route("detail")]
        public IHttpActionResult GetAccountDetail()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            var currentAccount = db.Users.Find(int.Parse(ID));
            return Ok(currentAccount);
        }

        //Search User for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("api/admin/userFinder/{userID}")]
        public IHttpActionResult FindUser(int userID)
        {
            var searchedUser = db.Users.Find(userID);
            return Ok(searchedUser);
        }

        //Update device Token
        [Authorize(Roles = Constant.MANAGER_ROLE)]
        [HttpPut]
        [Route("api/managerToken/{deviceToken}")]
        public void PutToken(string deviceToken)
        {
            var identity = (ClaimsIdentity)User.Identity;
            int id = int.Parse(identity.Claims.FirstOrDefault(c => c.Type == "ID").Value);
            using (db)
            {
                var entity = db.Users.FirstOrDefault(e => e.userId == id);
                entity.deviceToken = deviceToken;
                db.SaveChanges();
            }
        }

        //Send notify through deviceToken
        [HttpPost]
        [Route("api/test/Notification/{deviceID}")]
        public IHttpActionResult SendNotify(int deviceID)
        {
            string deviceToken = "cAF8JeveS9av5pIdQtge0-:APA91bGvzkAno7ycM_fIzqwEjhIUTBy-la9u71_" +
                "vYocHFhnnuGIO0PyfAMU2ph0cae6YuRGpYTAnbw9KtcKgN-aENmED3Bz4KLHnjrpU9HgfRHhBcTBP_" +
                "gbd41-tcsMD4kC9Vl0dnHC2";
            string now = DateTime.Now.ToString("H:mm");
            string room = "AZ1";

            string titleText = "Detect drowsiness!";
            string bodyText = "There was a drowsiness in " + room + deviceID + " at " + now;
            string randomNum = deviceID.ToString();

            //Nhận intent thẳng vào activity nhưng không generate đc nhiều notify
            //var data = new
            //{
            //    to = deviceToken,
            //    data = new
            //    {
            //        title = titleText,
            //        message = bodyText,
            //        userId = randomNum,
            //        status = true
            //    }
            //};
            
            //Phải qua app home page rồi ấn thêm lần nữa sẽ vào đc activity mong muốn.
            var messageInformation = new Message()
            {
                notification = new Notification()
                {
                    title = titleText,
                    body = bodyText
                },
                to = deviceToken
            };
            var serializer = new JavaScriptSerializer();
            var json = serializer.Serialize(messageInformation);
            byte[] byteArray = Encoding.UTF8.GetBytes(json);
            FirebaseNotification firebaseNotification = new FirebaseNotification();
            firebaseNotification.SendNotification(byteArray);
            return Ok();
        }

        [Route("locations")]
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