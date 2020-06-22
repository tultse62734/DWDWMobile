using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public class DeviceService : IDeviceService, IDisposable
    {
        private DWDBContext context;
        private bool disposed = false;
        public void DeactiveDevice(int deviceId)
        {
            throw new NotImplementedException();
        }
        public DeviceService(DWDBContext context)
        {
            this.context = context;
            context.Configuration.ProxyCreationEnabled = false;
        }
        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public Device GetDeviceById(int deviceId)
        {
            return context.Devices.Find(deviceId);
        }

        public IEnumerable<Device> GetDevices()
        {
            return context.Devices;
        }

        public void InsertDevice(Device device)
        {
            throw new NotImplementedException();
        }

        public void Save()
        {
            context.SaveChanges();
        }

        public IEnumerable<Device> GetDevicesInLocation(int locationId)
        {
            //check locationid
            var devices = from d in context.Devices
                          join rd in context.RoomDevices on d.deviceId equals rd.deviceId
                          join r in context.Rooms on rd.roomId equals r.roomId
                          where r.locationId == locationId
                          select d;
            return devices.ToList();
        }
    }
}