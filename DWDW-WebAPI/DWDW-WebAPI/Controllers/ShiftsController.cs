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
using DWDW_WebAPI.Contants;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.Services;
using DWDW_WebAPI.ViewModel;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/api/Shifts")]
    public class ShiftsController : ApiController
    {
        private DWDBContext db = new DWDBContext();
        private IShiftService shiftService;
        public ShiftsController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            shiftService = new ShiftService();
        }

        //Get all shift for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("GetAllShifts")]
        public IHttpActionResult GetAdminAllShifts()
        {
            try
            {
                var shifts = shiftService.GetAllAdminShifts();
                if (shifts != null)
                {
                    return Ok(shifts);
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

        //Search shift for  admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("GetShiftsByID")]
        public IHttpActionResult GetShiftsByIDAdmin(int id)
        {
            try
            {
                var shifts = shiftService.GetIDShift(id);
                if (shifts != null)
                {
                    return Ok(shifts);
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

        //Get shift for account
        [Authorize(Roles = Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        [HttpGet]
        [Route("GetShiftSubaccount")]
        public IHttpActionResult GetShiftSubaccount()
        {
            var listSubaccountShift = new List<Shift>();
            var identity = (ClaimsIdentity)User.Identity;
            var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(Id);
            try
            {
                listSubaccountShift = shiftService.GetSubaccountShift(accountId);
                if (listSubaccountShift != null)
                {
                    return Ok(listSubaccountShift);
                }
                else
                {
                    return BadRequest(ErrorMessage.GET_LIST_FAIL);
                }
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
        }
        //Get shift for account
        [Authorize(Roles = Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        [HttpGet]
        [Route("GetShiftSubaccountByID")]
        public IHttpActionResult GetShiftSubaccountByID(int shiftID)
        {
            var listSubaccountShift = new List<Shift>();
            var identity = (ClaimsIdentity)User.Identity;
            var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(Id);
            try
            {
                listSubaccountShift = shiftService.GetSubaccountShift(accountId);
                if (listSubaccountShift != null)
                {
                    var shiftSearch = listSubaccountShift.FirstOrDefault(x => x.shiftId == shiftID);
                    return Ok(shiftSearch);
                }
                else
                {
                    return BadRequest(ErrorMessage.GET_LIST_FAIL);
                }
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
        }

        //Create new shift for manager
        [Authorize(Roles = Constant.MANAGER_ROLE)]
        [HttpPost]
        [Route("PostShift")]
        public IHttpActionResult PostShift(ShiftPostPutModel sm)
        {
            var identity = (ClaimsIdentity)User.Identity;
            var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(Id);
            try
            {

                //Validate Manager to see if they qualify to use that location
                bool valid = shiftService.validateCreate(accountId, sm.userLocationId);
                //Validate RoomID belong to location
                bool valid2 = shiftService.validateCreateRoom(sm.roomId, sm.userLocationId);

                if(valid == true && valid2 == true)
                {
                    shiftService.CreateShift(sm);
                    shiftService.Save();
                    return Ok();
                }
                else
                {
                    return BadRequest(ErrorMessage.CREATE_FAIL);
                }
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.CREATE_FAIL);
            }
        }

        //Update-Edit existing shift for manager
        [Authorize(Roles = Constant.MANAGER_ROLE)]
        [HttpPut]
        [Route("PutShift/{id}")]
        public IHttpActionResult PutShift(int id, ShiftPostPutModel sm)
        {
            var identity = (ClaimsIdentity)User.Identity;
            var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(Id);
            try
            {
                var shift = shiftService.GetIDShift(id);
                if (shift != null)
                {
                    //Validate if shift belong to the manager
                    bool isBelonged = shiftService.validateBelongManager(accountId, shift.userLocationId);
                    //Validate if the new info is qualify for that manager
                    bool valid = shiftService.validateCreate(accountId, sm.userLocationId);
                    bool valid2 = shiftService.validateCreateRoom(sm.roomId, sm.userLocationId);

                    if (isBelonged == true && valid == true && valid2 == true)
                    {
                        shiftService.UpdateShift(shift, sm);
                        shiftService.Save();
                        return Ok();
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.UPDATE_FAIL);
                    }
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

        //Delete shift for manager
        [Authorize(Roles = Constant.MANAGER_ROLE)]
        [HttpDelete]
        [Route("DeleteShift")]
        public IHttpActionResult DeleteShift(int id)
        {
            var identity = (ClaimsIdentity)User.Identity;
            var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(Id);
            try
            {
                var shift = shiftService.GetIDShift(id);
                if (shift != null)
                {
                    bool isBelonged = shiftService.validateBelongManager(accountId, shift.userLocationId);
                    if (isBelonged)
                    {
                        shiftService.DeleteShift(shift);
                        shiftService.Save();
                        return Ok();
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.DELETE_FAIL);
                    }            
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

        //Get assigned shift
        [Authorize(Roles = Constant.WORKER_ROLE)]
        [HttpGet]
        public IHttpActionResult GetAssignedShiftWorker()
        {

            return Ok();
        }

    }
}