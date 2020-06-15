using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Security.Claims;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.Firebase;
using System.Web.Script.Serialization;
using System.Text;

namespace DWDW_WebAPI.Controllers
{
    public class UserController : ApiController
    {
        //Quyền truy cập dữ liệu cho mọi role
        [Authorize(Roles = "1, 2")]
        [HttpGet]
        [Route("api/test/defaultResource")]
        public IHttpActionResult GetDefaultResource()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            //var phone = identity.Claims.FirstOrDefault(c => c.Type == "Phone").Value;
            //var dateOfBirth = identity.Claims.FirstOrDefault(c => c.Type == "DateOfBirth").Value;
            //var Role = identity.RoleClaimType;
            //var Role = identity.Claims.FirstOrDefault(c => c.Type == "Role").Value;
            //var Gender = identity.Claims.FirstOrDefault(c => c.Type == "Gender").Value;
            //var Status = identity.Claims.FirstOrDefault(c => c.Type == "Status").Value;
            //var summary = "Hello: " + identity.Name + NewLine + "User ID: " + ID
            //    + NewLine + "Role: " + Role
            //    + NewLine + "Phone: " + phone
            //    + NewLine + "Date of Birth: " + dateOfBirth
            //    + NewLine + "Gender: " + Gender
            //    + NewLine + "Status: " + Status;
            //return Ok(summary);
            DWDWDBEntities entities = new DWDWDBEntities();
            var currentUser = entities.Users.Find(int.Parse(ID));
            return Ok(currentUser);
        }

        //Tài nguyên dành cho user có role là 1 - Admin
        [Authorize(Roles = "1")]
        [HttpGet]
        [Route("api/test/resourceAdmin")]
        public IHttpActionResult GetAdminResource()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var phone = identity.Claims.FirstOrDefault(c => c.Type == "Phone").Value;
            var fullName = identity.Name;
            return Ok("Hello " + fullName + ", Your phone number is: " + phone);
        }

        //Update device Token
        [Authorize(Roles = "2")]
        [HttpPut]
        [Route("api/managerToken/{deviceToken}")]
        public void Put(string deviceToken)
        {
            var identity = (ClaimsIdentity)User.Identity;
            int id = int.Parse(identity.Claims.FirstOrDefault(c => c.Type == "ID").Value);
            using (DWDWDBEntities entities = new DWDWDBEntities())
            {
                var entity = entities.Users.FirstOrDefault(e => e.userId == id);
                entity.deviceToken = deviceToken;

                entities.SaveChanges();
            }
        }

        //API send Notification
        [HttpGet]
        [Route("api/test/Notification/{room}")]
        public IHttpActionResult SendNotify(string room)
        {
            //room = "212";
            DWDWDBEntities entities = new DWDWDBEntities();
            var roomNoti = entities.Rooms.FirstOrDefault(e => e.roomName == room);
            var locationNoti = entities.Locations.FirstOrDefault(e => e.locationId == roomNoti.locationId);
            var managerNoti = entities.Users.FirstOrDefault(e => e.userId == locationNoti.managerId);
            string deviceToken = managerNoti.deviceToken;
            string now = DateTime.Now.ToString("H:mm");
            string titleText = "Detect drowsiness!";
            string bodyText = "There was a drowsiness in " + room + " at " + now;
            //string deviceToken = "cAF8JeveS9av5pIdQtge0-:APA91bGvzkAno7ycM_fIzqwEjhIUTBy-la9u71_" +
            //    "vYocHFhnnuGIO0PyfAMU2ph0cae6YuRGpYTAnbw9KtcKgN-aENmED3Bz4KLHnjrpU9HgfRHhBcTBP_" +
            //    "gbd41-tcsMD4kC9Vl0dnHC2";

            var data = new
            {
                to = deviceToken,
                data= new
                {
                    title = titleText,
                    body = bodyText,
                    message = bodyText
                }
            };
            var serializer = new JavaScriptSerializer();
            var json = serializer.Serialize(data);
            byte[] byteArray = Encoding.UTF8.GetBytes(json);
            FirebaseNotification firebaseNotification = new FirebaseNotification();
            firebaseNotification.SendNotification(byteArray);
            return Ok();
        }
    }
}