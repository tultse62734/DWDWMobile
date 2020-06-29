using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class UserViewModel
    {
        public int userId { get; set; }
        public string userName { get; set; }
        public string password { get; set; }
        public int? phone { get; set; }
        public DateTime? dateOfBirth { get; set; }
        public int? gender { get; set; }
        public string deviceToken { get; set; }
        public int? roleId { get; set; }
        public bool? isActive { get; set; }
    }

    public class UserGetAPIViewMode
    {
        
    }

}