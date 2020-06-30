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
    public class ShiftsController : BaseController
    {
        private DWDBContext db = new DWDBContext();
        private IShiftService shiftService;
        public ShiftsController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            shiftService = new ShiftService();
        }

        //Get shift
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpGet]
        [Route("")]
        public IHttpActionResult GetShifts()
        {
            var listSubaccountShift = new List<Shift>();
            var user = this.GetIndentiy();
            try
            {
                if (user.roleId == 1)
                {
                    var shifts = shiftService.GetAllAdminShifts();
                    if (shifts != null)
                    {
                        return Ok(shifts);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else if (user.roleId == 2)
                {
                    listSubaccountShift = shiftService.GetManagerShift(user.userId);
                    if (listSubaccountShift != null)
                    {
                        return Ok(listSubaccountShift);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else
                {
                    listSubaccountShift = shiftService.GetWorkerShift(user.userId);
                    if (listSubaccountShift != null)
                    {
                        return Ok(listSubaccountShift);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Search shift
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpGet]
        [Route("{id}")]
        public IHttpActionResult GetShiftsByIDAdmin(int id)
        {
            var listSubaccountShift = new List<Shift>();
            var user = this.GetIndentiy();
            try
            {
                if (user.roleId == 1)
                {
                    var shifts = shiftService.GetIDShift(id);
                    if (shifts != null)
                    {
                        return Ok(shifts);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else if (user.roleId == 2)
                {
                    listSubaccountShift = shiftService.GetManagerShift(user.userId);
                    if (listSubaccountShift != null)
                    {
                        var shiftSearch = listSubaccountShift.FirstOrDefault(x => x.shiftId == id);
                        return Ok(shiftSearch);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else
                {
                    listSubaccountShift = shiftService.GetWorkerShift(user.userId);
                    if (listSubaccountShift != null)
                    {
                        var searchShift = listSubaccountShift.FirstOrDefault(x => x.shiftId == id);
                        return Ok(searchShift);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Create new shift for manager
        //[Authorize(Roles = Constant.MANAGER_ROLE)]
        [Authorize]
        [HttpPost]
        [Route("")]
        public IHttpActionResult PostShift(ShiftPostPutModel sm)
        {
            var user = this.GetIndentiy();
            try
            {
                if (user.roleId == 2)
                {
                    //Validate Manager to see if they qualify to use that location
                    bool valid = shiftService.validateCreate(user.userId, sm.userLocationId);
                    //Validate RoomID belong to location
                    bool valid2 = shiftService.validateCreateRoom(sm.roomId, sm.userLocationId);

                    if (valid == true && valid2 == true)
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
                else
                {
                    return BadRequest(ErrorMessage.UNAUTHORIZED);
                }
                
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Update-Edit existing shift for manager
        //[Authorize(Roles = Constant.MANAGER_ROLE)]
        [Authorize]
        [HttpPut]
        [Route("{id}")]
        public IHttpActionResult PutShift(int id, ShiftPostPutModel sm)
        {
            var user = this.GetIndentiy();
            try
            {
                if (user.roleId == 2)
                {
                    var shift = shiftService.GetIDShift(id);
                    if (shift != null)
                    {
                        //Validate if shift belong to the manager
                        bool isBelonged = shiftService.validateBelongManager(user.userId, shift.userLocationId);
                        //Validate if the new info is qualify for that manager
                        bool valid = shiftService.validateCreate(user.userId, sm.userLocationId);
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
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else
                {
                    return BadRequest(ErrorMessage.UNAUTHORIZED);
                }
                
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        //Delete shift for manager
        //[Authorize(Roles = Constant.MANAGER_ROLE)]
        [Authorize]
        [HttpDelete]
        [Route("")]
        public IHttpActionResult DeleteShift(int id)
        {
            var user = this.GetIndentiy();
            try
            {
                if(user.roleId == 2)
                {
                    var shift = shiftService.GetIDShift(id);
                    if (shift != null)
                    {
                        bool isBelonged = shiftService.validateBelongManager(user.userId, shift.userLocationId);
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
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else
                {
                    return BadRequest(ErrorMessage.UNAUTHORIZED);
                }
                
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

    }
}