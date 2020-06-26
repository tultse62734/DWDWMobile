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
    public class DevicesController : ApiController
    {
        private DWDBContext db = new DWDBContext();
        private IDeviceService deviceService;
        public DevicesController()
        {
            deviceService = new DeviceService();
        }
        
        //Get all device for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("")]
        public IHttpActionResult GetDevices()
        {
            try
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
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
        }

        //Search device for  admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("GetDevices")]
        public IHttpActionResult GetDevicesByIDAdmin(int id)
        {
            try
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
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
        }

        //View assigned device of manager and worker account
        [Authorize(Roles = Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        [HttpGet]
        [Route("GetSubAccountDevices")]
        public IHttpActionResult GetSubDevices()
        {
            var identity = (ClaimsIdentity)User.Identity;
            var Id = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
            int accountId = Convert.ToInt32(Id);
            //Future list
            var deviceTotal = new List<Device>();
            try
            {
                //Get related location for user
                var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == accountId)).ToList();
                if (locationList != null)
                {
                    int locationCount = locationList.Count();
                    for (int i = 0; i < locationCount; i++)
                    {
                        var currentLocation = locationList.ElementAt(i);
                        deviceService.getDeviceListFromSingleLocation(currentLocation, deviceTotal);
                    }
                }
                else
                {
                    return BadRequest(ErrorMessage.EMPTY_LIST);
                }
                
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
            return Ok(deviceTotal);
        }

        //Get Device list from single location
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("GetDevicesFromLocation")]
        public IHttpActionResult GetDevicesFromLocation(int id)
        {
            //Future list
            var deviceTotal = new List<Device>();
            try
            {
                //replace bang location service check exist
                var location = db.Locations.Find(id);
                if (location != null)
                {
                    deviceService.GetDeviceListFromSingleLocation(location, deviceTotal);
                    return Ok(deviceTotal);
                }
                else
                {
                    return BadRequest(ErrorMessage.EMPTY_LIST);
                }
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }            
        }

        

        //Create new device for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPost]
        [Route("PostDevices")]
        public IHttpActionResult PostDevices(DevicePostPutModel dm)
        {
            try
            {
                deviceService.CreateDevice(dm);
                deviceService.Save();
                return Ok();
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.CREATE_FAIL);
            }   
        }

        //Update existing info device for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPut]
        [Route("PutDevices")]
        public IHttpActionResult PutDevices(int id, DevicePostPutModel dm)
        {
            try
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
            catch (Exception)
            {
                return BadRequest(ErrorMessage.UPDATE_FAIL);
            }
        }

        //Change device active
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPut]
        [Route("PutDevicesActive")]
        public IHttpActionResult PutDevicesActive(int id, DeviceStatusModel dm)
        {
            try
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
            catch(Exception)
            {
                return BadRequest(ErrorMessage.UPDATE_FAIL);
            }
        }

        [HttpGet]
        [Route("Test")]
        public IHttpActionResult TestXCXC(int c)
        {
            //var deviceTotal = new List<Device>();
            //var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == 3)).ToList();
            //int locationCount = locationList.Count();
            //for (int i = 0; i < locationCount; i++)
            //{
            //    var currentLocation = locationList.ElementAt(i);
            //    var roomList = db.Rooms.Where(x => x.locationId == currentLocation.locationId).ToList();
            //    var roomCount = roomList.Count();
            //    for (int y = 0; y < roomCount; y++)
            //    {
            //        var currentRoom = roomList.ElementAt(y);
            //        var devicee = db.Devices.Where(a => a.RoomDevices.Any(b => b.roomId == currentRoom.roomId)).ToList();
            //        deviceTotal.AddRange(devicee);
            //    }
            //}
            //return Ok(deviceTotal); 
            return Ok();
        }
    }
}