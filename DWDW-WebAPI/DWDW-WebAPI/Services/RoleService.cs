using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface IRoleService
    {
        List<RoleViewModel> GetAllAdminRoles();
        Role GetIDRole(int id);
        void CreateRole(RolePostPutModel rm);
        void UpdateRole(Role role, RolePostPutModel rm);
        void Save();
        void UpdateStatusRole(Role role, RoleisActive rm);
        void DeleteRole(Role role);
    }
    public class RoleService : IRoleService
    {
        private DWDBContext db;

        public RoleService()
        {
            this.db = new DWDBContext();
            db.Configuration.ProxyCreationEnabled = false;
        }
        public List<RoleViewModel> GetAllAdminRoles()
        {
            var result = db.Roles.Select(x => new RoleViewModel
            {
                roleId = x.roleId,
                roleName = x.roleName,
                isActive = x.isActive
            }).ToList();
            return result;
        }

        public Role GetIDRole(int id)
        {
            return db.Roles.Find(id);
        }

        public void CreateRole(RolePostPutModel rm)
        {
            var roles = db.Roles;
            var r = roles.Add(new Role()
            {
                roleName = rm.roleName,
                isActive = rm.isActive
            });
        }

        public void UpdateRole(Role role, RolePostPutModel rm)
        {
            role.roleName = rm.roleName;
            role.isActive = rm.isActive;
        }

        public void Save()
        {
            db.SaveChanges();
        }

        public void UpdateStatusRole(Role role, RoleisActive rm)
        {
            role.isActive = rm.isActive;
        }
        public void DeleteRole(Role role)
        {
            db.Roles.Remove(role);
        }

    }
}