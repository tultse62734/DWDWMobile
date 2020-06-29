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
        List<DeviceViewModel> GetDevices();
        Device GetDeviceByID(int id);
        void CreateDevice(DevicePostPutModel dm);
        void UpdateDevice(Device device, DevicePostPutModel dm);
        void Save();
        bool DeviceExists(int deviceID);
        void GetDeviceListFromSingleLocation(Location currentLocation, List<Device> device);
        void UpdateStatusDevice(Device device, DeviceStatusModel dm);
    }
    public class DeviceService : IDeviceService
    {
        private DWDBContext db;

        public DeviceService()
        {
            this.db = new DWDBContext();
            db.Configuration.ProxyCreationEnabled = false;
        }

        public List<DeviceViewModel> GetDevices()
        {
            var devices = db.Devices;
            var result = db.Devices.Select(x => new DeviceViewModel
            {
                deviceId = x.deviceId,
                deviceCode = x.deviceCode,
                deviceStatus = x.deviceStatus,
                isActive = x.isActive
            }).ToList();
            return result;
        }

        public Device GetDeviceByID(int id)
        {
            return db.Devices.Find(id);
        }

        public void CreateDevice(DevicePostPutModel dm)
        {
            var devices = db.Devices;
            var d = devices.Add(new Device()
            {
                deviceCode = dm.deviceCode,
                deviceStatus = dm.deviceStatus,
                isActive = dm.isActive
            });
        }

        public void UpdateDevice(Device device, DevicePostPutModel dm)
        {
            device.deviceCode = dm.deviceCode;
            device.deviceStatus = dm.deviceStatus;
            device.isActive = dm.isActive;
        }

        public void Save()
        {
            db.SaveChanges();
        }

        public bool DeviceExists(int deviceID)
        {
            return db.Devices.Count(a => a.deviceId == deviceID) > 0;
        }

        public void GetDeviceListFromSingleLocation(Location currentLocation, List<Device> device)
        {
            var roomList = db.Rooms.Where(x => x.locationId == currentLocation.locationId).ToList();
            var roomCount = roomList.Count();
            for (int y = 0; y < roomCount; y++)
            {
                var currentRoom = roomList.ElementAt(y);
                var devicee = db.Devices.Where(a => a.RoomDevices.Any(b => b.roomId == currentRoom.roomId && b.isActive == true)).ToList();
                device.AddRange(devicee);
            }
        }

        public void UpdateStatusDevice(Device device, DeviceStatusModel dm)
        {
            device.isActive = dm.isActive;
        }
    }
}