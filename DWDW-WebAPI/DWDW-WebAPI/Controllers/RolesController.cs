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
    public class RolesController : ApiController
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
        [Route("GetAllRoles")]
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
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
        }

        //Search device for  admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("GetRoles/{id}")]
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
                return BadRequest(ErrorMessage.SEARCH_FAIL);
            }
        }

        //Create new role for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPost]
        [Route("PostRoles")]
        public IHttpActionResult PostDevices(RolePostPutModel rm)
        {
            try
            {
                roleService.CreateRole(rm);
                roleService.Save();
                return Ok();
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.CREATE_FAIL);
            }
        }

        //Update-Edit existing role for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPut]
        [Route("PutRoles/{id}")]
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
                return BadRequest(ErrorMessage.UPDATE_FAIL);
            }
        }

        //Change role active for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPut]
        [Route("PutRolesActive/{id}")]
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
                return BadRequest(ErrorMessage.UPDATE_FAIL);
            }
        }

        //Delete role for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpDelete]
        [Route("DeleteRoles/{id}")]
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
                return BadRequest(ErrorMessage.DELETE_FAIL);
            }       
        }


    }
}