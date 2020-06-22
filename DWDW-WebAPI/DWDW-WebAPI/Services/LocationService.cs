using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public class LocationService : ILocationService, IDisposable
    {
        private DWDBContext context;
        private bool disposed = false;

        public LocationService(DWDBContext context)
        {
            this.context = context;
            context.Configuration.ProxyCreationEnabled = false;

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

        public void InsertLocation(Location location)
        {
            context.Locations.Add(location);
        }

        public void DeactiveLocation(Location location)
        {
            location.isActive = false;
            context.Entry(location).State = EntityState.Modified;
           
        }

        public void Save()
        {
            context.SaveChanges();
        }

        public List<Location> GetAssignedLocations(int userId)
        {
            return context.Locations
                .Where(a => a.UserLocations.Any(b => b.userId == userId))
                .ToList();
        }

        public void UpdateLocation(Location location)
        {
            context.Entry(location).State = EntityState.Modified;
        }

        public bool LocationExists(int locationId)
        {
            return context.Locations.Count(l => l.locationId == locationId) > 0;
        }
    }
}