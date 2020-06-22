using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface IDeviceService
    {
        DeviceViewModel GetDevice();
        DeviceViewModel GetIDDevice(int id);
        DevicePostPutModel PostDevice(DevicePostPutModel dm);
        DeviceStatusModel UpdateStatusDevice(int id, int status);
    }
    public class DeviceService : IDeviceService
    {
        private DWDBContext db;

        public DeviceService()
        {
            this.db = new DWDBContext();

        }

        public DeviceViewModel GetDevice()
        {
            var devices = db.Devices.Select(x => new DeviceViewModel
            {
                deviceId = x.deviceId,
                deviceCode = x.deviceCode,
                deviceStatus = x.deviceStatus,
                isActive = x.isActive
            }).ToList();
            throw new NotImplementedException();
        }

        public DeviceViewModel GetIDDevice(int id)
        {
            var device = db.Devices.Find(id);
            throw new NotImplementedException();
        }

        public DevicePostPutModel PostDevice(DevicePostPutModel dm)
        {
            throw new NotImplementedException();

        }

        public DeviceStatusModel UpdateStatusDevice(int id, int status)
        {
            var devices = db.Devices.FirstOrDefault(e => e.deviceId == id);
            devices.deviceStatus = status;
            throw new NotImplementedException();
        }
    }
}