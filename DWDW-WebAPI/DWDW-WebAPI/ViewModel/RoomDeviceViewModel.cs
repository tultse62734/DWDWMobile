using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class RoomDeviceViewModel
    {
        public int roomDeviceId { get; set; }
        public int? roomId { get; set; }
        public int? deviceId { get; set; }
        public DateTime? startDate { get; set; }
        public DateTime? endDate { get; set; }
        public bool? isActive { get; set; }
    }
}