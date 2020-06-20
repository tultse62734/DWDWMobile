using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class RoomViewModel
    {
        public int roomId { get; set; }
        public string roomCode { get; set; }
        public int? locationId { get; set; }
        public bool? isActive { get; set; }
    }
}