using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using DWDW_WebAPI.Models;

namespace DWDW_WebAPI.Services
{
    public class UserSecurityService : IDisposable
    {
        DWDBContext context = new DWDBContext();
        //Kiểm tra thông tin người dùng login
        public User ValidateUser(string username, string password)
        {
            return context.Users.FirstOrDefault(user => user.userName.Equals(username, StringComparison.OrdinalIgnoreCase)
            && user.password == password);
        }
        public void Dispose()
        {
            context.Dispose();
        }
    }
}