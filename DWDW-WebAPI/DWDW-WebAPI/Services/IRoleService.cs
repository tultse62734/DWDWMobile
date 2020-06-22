using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DWDW_WebAPI.Services
{
    public interface IRoleService : IDisposable
    {
        IEnumerable<Role> GetRoles();
        Role GetRoleById(int roleId);
        void InsertRole(Role role);
    }
}
