using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class RecordViewModel
    {
        public int recordId { get; set; }
        public int? deviceId { get; set; }
        public DateTime? recordDate { get; set; }
        public string image { get; set; }
        public int? recordStatus { get; set; }
        public bool? isActive { get; set; }
    }

}