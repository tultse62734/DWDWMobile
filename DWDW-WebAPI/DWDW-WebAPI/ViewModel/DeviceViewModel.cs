using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class DeviceViewModel
    {
        public int deviceId { get; set; }
        public string deviceCode { get; set; }
        public int? deviceStatus { get; set; }
        public bool? isActive { get; set; }
    }
}