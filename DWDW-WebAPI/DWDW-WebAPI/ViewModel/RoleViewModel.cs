using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class RoleViewModel
    {
        public int roleId { get; set; }
        public string roleName { get; set; }
        public bool? isActive { get; set; }
    }
}