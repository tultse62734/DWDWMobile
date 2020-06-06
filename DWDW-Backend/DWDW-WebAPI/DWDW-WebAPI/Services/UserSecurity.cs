using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using DWDW_WebAPI.Models;

namespace DWDW_WebAPI.Services
{
    public class UserSecurity : IDisposable
    {
        DWDWDBEntities context = new DWDWDBEntities();
        //Kiểm tra thông tin người dùng login
        public User ValidateUser(string fullName, string password)
        {
            return context.Users.FirstOrDefault(user => user.fullName.Equals(fullName, StringComparison.OrdinalIgnoreCase)
            && user.password == password);
        }
        public void Dispose()
        {
            context.Dispose();
        }
    }
}