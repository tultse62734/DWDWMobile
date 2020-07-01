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
using DWDW_WebAPI.Contants;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.Services;
using DWDW_WebAPI.ViewModel;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/api/Roles")]
    public class RolesController : BaseController
    {
        private DWDBContext db = new DWDBContext();
        private IRoleService roleService;
        public RolesController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            roleService = new RoleService();
        }

        //Get all role for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("GetRoles")]
        public IHttpActionResult GetAdminAllRoles()
        {
            try
            {
                var roles = roleService.GetAllAdminRoles();
                if (roles != null)
                {
                    return Ok(roles);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Search role for  admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("SearchRoles")]
        public IHttpActionResult GetRolesByIDAdmin(int id)
        {
            try
            {
                var roles = roleService.GetIDRole(id);
                if (roles != null)
                {
                    return Ok(roles);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Create new role for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPost]
        [Route("CreateRoles")]
        public IHttpActionResult PostRole(RolePostPutModel rm)
        {
            try
            {
                roleService.CreateRole(rm);
                roleService.Save();
                return Ok();
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Update-Edit existing role for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPut]
        [Route("UpdateRoles")]
        public IHttpActionResult PutRoless(int id, RolePostPutModel rm)
        {
            try
            {
                var role = roleService.GetIDRole(id);
                if (role != null)
                {
                    roleService.UpdateRole(role, rm);
                    roleService.Save();
                    return Ok();
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Change role active for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPut]
        [Route("UpdateRolesActive")]
        public IHttpActionResult PutRolesActive(int id, RoleisActive rm)
        {
            try
            {
                var roles = roleService.GetIDRole(id);
                if (roles == null)
                {
                    return NotFound();
                }
                else
                {
                    roleService.UpdateStatusRole(roles, rm);
                    roleService.Save();
                    return Ok();
                }
            }
            catch (Exception e)
            {
                throw new Exception();
            }
        }

        //Delete role for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpDelete]
        [Route("DeleteRoles")]
        public IHttpActionResult DeleteRole(int id)
        {
            try
            {
                var role = roleService.GetIDRole(id);
                if (role != null)
                {
                    roleService.DeleteRole(role);
                    roleService.Save();
                    return Ok();
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }


    }
}