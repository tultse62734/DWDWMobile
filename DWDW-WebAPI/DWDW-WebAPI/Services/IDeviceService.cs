using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DWDW_WebAPI.Services
{
    public interface IDeviceService : IDisposable
    {
        IEnumerable<Device> GetDevices();
        Device GetDeviceById(int deviceId);
        void InsertDevice(Device device);
        void DeactiveDevice(int deviceId);
        void Save();
        IEnumerable<Device> GetDevicesInLocation(int locationId);

    }
}
