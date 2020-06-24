using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface IUserService : IDisposable
    {
        IEnumerable<User> GetUsers();
        User GetUserById(int userId);
        void InsertUser(User user);

    }
    public class UserService : IUserService, IDisposable
    {

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public Shift GetShiftById(int shiftId)
        {
            throw new NotImplementedException();
        }

        public User GetUserById(int userId)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<User> GetUsers()
        {
            throw new NotImplementedException();
        }

        public void InsertUser(User user)
        {
            throw new NotImplementedException();
        }
    }
}