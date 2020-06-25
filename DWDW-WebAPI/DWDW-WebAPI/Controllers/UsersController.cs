using DWDW_WebAPI.Firebase;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.Services;
using DWDW_WebAPI.ViewModel;
using System;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Security.Claims;
using System.Text;
using System.Web.Http;
using System.Web.Http.Description;
using System.Web.Script.Serialization;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/api/Users")]
    public class UsersController : ApiController
    {
        private IUserService userService;
        private ModelMapping modelMapping;

        public UsersController()
        {
            this.userService = new UserService(new DWDBContext());
            this.modelMapping = new ModelMapping();
        }
        // GET ALL User for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        public IHttpActionResult GetUsers()
        {
            var userList = userService.GetUsers();
            return Ok(userList);
        }
        //hoang
        //?
        //Get user detail
        //[Authorize(Roles = Constant.ADMIN_ROLE + "," + Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        [HttpGet]
        [Route("detail")]
        public IHttpActionResult GetAccountDetail()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            var currentAccount = userService.GetUserById(int.Parse(ID));
            return Ok(currentAccount);
        }
        //api cũ là search user, nhưng theo id
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("{userId}")]
        [ResponseType(typeof(User))]
        public IHttpActionResult GetUserById(int userId)
        {
            var user = userService.GetUserById(userId);
            if (user == null)
            {
                return NotFound();
            }
            return Ok(user);
        }

        //hoang
        //Update device Token
        //[Authorize(Roles = Constant.MANAGER_ROLE)]
        //[HttpPut]
        //[Route("api/managerToken/{deviceToken}")]
        //public void PutToken(string deviceToken)
        //{
        //    var identity = (ClaimsIdentity)User.Identity;
        //    int id = int.Parse(identity.Claims.FirstOrDefault(c => c.Type == "ID").Value);
        //    using (db)
        //    {
        //        var entity = db.Users.FirstOrDefault(e => e.userId == id);
        //        entity.deviceToken = deviceToken;
        //        db.SaveChanges();
        //    }
        //}

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

        // PUT: api/Users/5
        [HttpPut]
        [Route("")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUser(int id, UserViewModel userViewModel)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                if (userViewModel.userId != id) return BadRequest();
                User user = userService.GetUserById(id);
                if (user == null) return NotFound();
                modelMapping.UpdateUserMapping(userViewModel, user);
                if (userService.UpdateUser(user))
                {
                    return Ok("Update succeed.");
                }
                else
                {
                    return BadRequest("Can not update User.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {
                return InternalServerError();
            }
        }

        // POST: api/Users
        [HttpPost]
        [Route("")]
        [ResponseType(typeof(User))]
        public IHttpActionResult PostUser(UserViewModel userViewModel)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                //mapping
                User user = modelMapping.CreateUserMapping(userViewModel);
                if (userService.InsertUser(user))
                {
                    return Ok("Insert succeed.");
                }
                else
                {
                    return BadRequest("Can not insert User.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {
                if (userService.UserExists(userViewModel.userId))
                {
                    return Conflict();
                }
                return InternalServerError();
            }
        }

        // PUT Deactive: api/Users/5
        [HttpPut]
        [Route("{userId}/deactive")]
        [ResponseType(typeof(User))]
        public IHttpActionResult PutUserDeactive(int userId)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                User user = userService.GetUserById(userId);
                if (user == null) return NotFound();
                if (user.isActive.Equals(false))
                {
                    return BadRequest("Location already deactivated.");
                }
                //mapping
                if (userService.DeactiveUser(user))
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