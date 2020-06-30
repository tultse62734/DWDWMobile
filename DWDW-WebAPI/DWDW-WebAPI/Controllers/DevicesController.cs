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
    [RoutePrefix("v1/api/Devices")]
    public class DevicesController : BaseController
    {
        private DWDBContext db = new DWDBContext();
        private IDeviceService deviceService;
        public DevicesController()
        {
            deviceService = new DeviceService();
        }
        
        //Get device
        //[Authorize(Roles = "1")]
        [Authorize]
        [HttpGet]
        [Route("")]
        public IHttpActionResult GetDevices()
        {
            var user = this.GetIndentiy();
            var deviceSubAccount = new List<Device>();
            var deviceSubAccountModel = new List<DeviceViewModel>();
            try
            {
                if (user.roleId == 1)
                {
                    var devices = deviceService.GetDevices();
                    if (devices != null)
                    {
                        return Ok(devices);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else
                {
                    var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == user.userId)).ToList();
                    if (locationList != null)
                    {
                        int locationCount = locationList.Count();
                        for (int i = 0; i < locationCount; i++)
                        {
                            var currentLocation = locationList.ElementAt(i);
                            deviceService.GetDeviceListFromSingleLocation(currentLocation, deviceSubAccount, deviceSubAccountModel);
                        }
                        return Ok(deviceSubAccountModel);
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

        //Search device
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpGet]
        [Route("{id}")]
        public IHttpActionResult GetDevicesByID(int id)
        {
            var user = this.GetIndentiy();
            var deviceSubAccount = new List<Device>();
            var deviceSubAccountModel = new List<DeviceViewModel>();
            try
            {
                if (user.roleId == 1)
                {
                    var devices = deviceService.GetDeviceByID(id);
                    if (devices != null)
                    {
                        return Ok(devices);
                    }
                    else
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                }
                else {
                    var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == user.userId)).ToList();
                    if (locationList != null)
                    {
                        int locationCount = locationList.Count();
                        for (int i = 0; i < locationCount; i++)
                        {
                            var currentLocation = locationList.ElementAt(i);
                            deviceService.GetDeviceListFromSingleLocation(currentLocation, deviceSubAccount, deviceSubAccountModel);
                        }
                        var deviceSearch = deviceSubAccountModel.FirstOrDefault(x => x.deviceId == id); ;
                        return Ok(deviceSearch);
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

        //Get Device list from single location
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("FromLocation/{locationID}")]
        public IHttpActionResult GetDevicesFromLocation(int locationID)
        {
            //Future list
            var deviceTotal = new List<Device>();
            var deviceSubAccountModel = new List<DeviceViewModel>();
            try
            {
                //replace bang location service check exist
                var location = db.Locations.Find(locationID);
                if (location != null)
                {
                    deviceService.GetDeviceListFromSingleLocation(location, deviceTotal, deviceSubAccountModel);
                    return Ok(deviceSubAccountModel);
                }
                else
                {
                    return BadRequest(ErrorMessage.EMPTY_LIST);
                }
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }



        //Create new device for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpPost]
        [Route("")]
        public IHttpActionResult PostDevices(DevicePostPutModel dm)
        {
            var user = this.GetIndentiy();
            try
            {
                if (user.roleId == 1)
                {
                    deviceService.CreateDevice(dm);
                    deviceService.Save();
                    return Ok();
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

        //Update existing info device for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpPut]
        [Route("")]
        public IHttpActionResult PutDevices(int id, DevicePostPutModel dm)
        {
            var user = this.GetIndentiy();
            try
            {
                if (user.roleId == 1)
                {
                    var device = deviceService.GetDeviceByID(id);
                    if (deviceService.DeviceExists(id))
                    {
                        deviceService.UpdateDevice(device, dm);
                        deviceService.Save();
                        return Ok();
                    }
                    else
                    {
                        return NotFound();
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

        //Change device active
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpPut]
        [Route("PutDevicesActive/{id}")]
        public IHttpActionResult PutDevicesActive(int id, DeviceStatusModel dm)
        {
            var user = this.GetIndentiy();
            try
            {
                if (user.roleId == 1)
                {
                    var devices = deviceService.GetDeviceByID(id);
                    if (devices == null)
                    {
                        return BadRequest(ErrorMessage.EMPTY_LIST);
                    }
                    else
                    {
                        deviceService.UpdateStatusDevice(devices, dm);
                        deviceService.Save();
                        return Ok();
                    }
                }
                else
                {
                    return BadRequest(ErrorMessage.UNAUTHORIZED);
                }
                
            }
            catch(Exception)
            {
                throw new Exception();
            }
        }

        [HttpGet]
        [Route("Test")]
        public IHttpActionResult TestXCXC(int c)
        {
            return Ok();
        }
    }
}