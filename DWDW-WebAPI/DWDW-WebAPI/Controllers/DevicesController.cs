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
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
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
                    return NotFound();
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        //Search device for  admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
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
                    return NotFound();
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        //View assigned device of manager and worker account
        //[Authorize(Roles = Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        [HttpGet]
        [Route("GetSubAccountDevices")]
        public IHttpActionResult GetSubDevices()
        {
            //Future list
            var deviceTotal = new List<Device>();
            try
            {
                //Get related location for user
                int currentUserID = 3;
                var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == currentUserID)).ToList();
                if (locationList != null)
                {
                    int locationCount = locationList.Count();
                    for (int i = 0; i < locationCount; i++)
                    {
                        var currentLocation = locationList.ElementAt(i);
                        deviceService.GetDeviceListFromSingleLocation(currentLocation, deviceTotal);
                        //deviceTotal.AddRange(devices);
                    }
                }
                else
                {
                    return NotFound();
                }
                
            }
            catch (Exception)
            {
                return BadRequest();
            }
            return Ok(deviceTotal);
        }

        //Get Device list from single location
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
                    return NotFound();
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }            
        }

        

        //Create new device for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
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
                return BadRequest();
            }   
        }

        //Update existing info device for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
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
                return BadRequest();
            }
        }

        //Change device active
        [HttpPut]
        [Route("PutDevicesActive")]
        public IHttpActionResult PutDevicesActive(int id, DeviceStatusModel dm)
        {
            try
            {
                var devices = deviceService.GetDeviceByID(id);
                if (devices == null)
                {
                    return NotFound();
                }
                else
                {
                    deviceService.UpdateStatusDevice(devices, dm);
                    deviceService.Save();
                    return Ok();
                }
            }
            catch(Exception e)
            {
                return BadRequest();
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