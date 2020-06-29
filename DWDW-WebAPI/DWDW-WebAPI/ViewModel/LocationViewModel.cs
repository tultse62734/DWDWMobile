using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class LocationViewModel
    {
        public int locationId { get; set; }
        public string locationCode { get; set; }
        public bool? isActive { get; set; }
    }
}