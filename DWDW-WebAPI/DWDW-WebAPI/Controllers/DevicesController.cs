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
        public DevicesController()
        {
            db.Configuration.ProxyCreationEnabled = false;
        }
        private DWDBContext db = new DWDBContext();
        private DeviceService ds = new DeviceService();

        //Get all device for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("admin/Devices")]
        public IHttpActionResult GetDevices()
        {
            //var devices = ds.GetDevice();
            var devices = db.Devices.ToList();
            return Ok(devices);
        }

        //Search device for  admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("admin/Devices/{id}")]
        public IHttpActionResult GetDevicesByID(int id)
        {
            //var devices = ds.GetIDDevice(id);
            var devices = db.Devices.Find(id);
            return Ok(devices);
        }

        //Create new device for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPost]
        [Route("postDevices")]
        public IHttpActionResult PostDevices(DevicePostPutModel dm)
        {
            var devices = db.Devices;
            var d = devices.Add(new Device()
            {
                deviceCode = dm.deviceCode,
                deviceStatus = dm.deviceStatus,
                isActive = dm.isActive
            });
            //db.Devices.Add(dm);
            db.SaveChanges();
            return Ok();
        }

        //Update existing device for admin
        [Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpPost]
        [Route("putDevices/{id}")]
        public IHttpActionResult PutDevices(int id, DevicePostPutModel dm)
        {
            var devices = db.Devices.FirstOrDefault(x => x.deviceId == id);
            devices.deviceCode = dm.deviceCode;
            devices.deviceStatus = dm.deviceStatus;
            devices.isActive = dm.isActive;
            db.SaveChanges();
            return Ok();
        }


        //View assigned device of manager and worker account
        //[Authorize(Roles = Constant.MANAGER_ROLE + "," + Constant.WORKER_ROLE)]
        //[HttpGet]
        //[Route("sub/Devices")]
        //public IHttpActionResult GetSubDevices()
        //{
        //    var identity = (ClaimsIdentity)User.Identity;
        //    var ID = identity.Claims.FirstOrDefault(c => c.Type == "ID").Value;
        //    int accountID = Convert.ToInt32(ID);

        //    var locationList = db.Locations.Where(a => a.UserLocations.Any(b => b.userId == accountID)).ToList();
        //    var devices = ds.GetDevice();
        //    return Ok(devices);
        //}




    }
}