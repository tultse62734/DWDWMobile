using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Claims;
using System.Web.Http;

namespace DWDW_WebAPI.Controllers
{
    public class BaseController : ApiController
    {
        protected User GetIndentiy()
        {
            var identity = User.Identity as ClaimsIdentity;
            if (identity != null)
            {
                IEnumerable<Claim> claims = identity.Claims;
                var id = claims.Where(p => p.Type == "id").FirstOrDefault()?.Value;
                var username = claims.Where(p => p.Type == "username").FirstOrDefault()?.Value;
                var role = claims.Where(p => p.Type == "roleId").FirstOrDefault()?.Value;

                return new User
                {
                    userId = int.Parse(id),
                    userName = username,
                    roleId = int.Parse(role),
                };

            }
            return null;
        }
    }
}
