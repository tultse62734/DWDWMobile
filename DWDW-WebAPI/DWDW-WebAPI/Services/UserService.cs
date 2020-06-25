using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface IUserService : IDisposable
    {
        IEnumerable<User> GetUsers();
        User GetUserById(int userId);
        void InsertUser(User user);
        Task<UserViewModel> LoginAsync(string username, string password);
    }
    public class UserService : IUserService, IDisposable
    {
        private DWDBContext db;
        public UserService()
        {
            db = new DWDBContext();
        }

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

        public async Task<UserViewModel> LoginAsync(string username, string password)
        {
            //check validate fields

            UserViewModel result = null;
            //get User by username password
            var user = await db.Users.FirstOrDefaultAsync(x => x.userName.Equals(username)
            && x.password.Equals(password));

            if(user != null)
            {
                result = new UserViewModel
                {
                    userId = user.userId,
                    userName = user.userName,
                    roleId = user.roleId,
                };
            }
            return result;
        }
    }
}