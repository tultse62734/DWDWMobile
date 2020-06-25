using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Transactions;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface ILocationService : IDisposable
    {
        IEnumerable<Location> GetLocations();
        Location GetLocationById(int locationId);
        bool InsertLocation(Location location);
        bool UpdateLocation(Location location);
        bool DeactiveLocation(Location location);
        List<Location> GetAssignedLocations(int userId);
        bool LocationExists(int locationId);
    }
    public class LocationService : ILocationService, IDisposable
    {
        private DWDBContext context;
        private bool disposed = false;

        public LocationService(DWDBContext context)
        {
            this.context = context;
        }
        protected virtual void Dispose(bool disposing)
        {
            if (!this.disposed)
            {
                if (disposing)
                {
                    context.Dispose();
                }
            }
            this.disposed = true;
        }
        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        public Location GetLocationById(int locationId)
        {
            return context.Locations.Find(locationId);
        }

        public IEnumerable<Location> GetLocations()
        {
            return context.Locations.Where(l => l.isActive == true).ToList();
        }

        public bool InsertLocation(Location location)
        {
            try
            {
                using (TransactionScope scope = new TransactionScope(TransactionScopeOption.RequiresNew))
                {
                    context.Locations.Add(location);
                    context.SaveChanges();
                    scope.Complete();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
                throw;
            }
        }

        public bool DeactiveLocation(Location location)
        {
            try
            {
                using (TransactionScope scope = new TransactionScope(TransactionScopeOption.RequiresNew))
                {
                    location.isActive = false;
                    context.Entry(location).State = EntityState.Modified;
                    context.SaveChanges();
                    scope.Complete();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
                throw;
            }
        }

        public List<Location> GetAssignedLocations(int userId)
        {
            return context.Locations
                .Where(a => a.UserLocations.Any(b => b.userId == userId))
                .ToList();
        }

        public bool UpdateLocation(Location location)
        {
            try
            {
                using (TransactionScope scope = new TransactionScope(TransactionScopeOption.RequiresNew))
                {
                    context.Entry(location).State = EntityState.Modified;
                    context.SaveChanges();
                    scope.Complete();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
                throw;
            }
        }

        public bool LocationExists(int locationId)
        {
            return context.Locations.Count(l => l.locationId == locationId) > 0;
        }
    }
}