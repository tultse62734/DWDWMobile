using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class ShiftViewModel
    {
        public int shiftId { get; set; }
        public int? userLocationId { get; set; }
        public DateTime? startDate { get; set; }
        public DateTime? endDate { get; set; }
        public int? roomId { get; set; }
        public int? shiftType { get; set; }
        public bool? isActive { get; set; }
    }
}