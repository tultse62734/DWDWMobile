using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Security.Claims;
using DWDW_WebAPI.Models;

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

        // Get User List
        public IEnumerable<User> Get()
        {
            using (DWDWDBEntities entities = new DWDWDBEntities())
            {
                return entities.Users.ToList();
            }
        }

        //Get User List by ID
        public IEnumerable<User> Get(int id)
        {
            using (DWDWDBEntities entities = new DWDWDBEntities())
            {
                var currentUser = entities.Users.Find(id);
                yield return currentUser;
            }
        }
    }
}