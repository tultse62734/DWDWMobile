using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DWDW_WebAPI.Services
{
    public interface ILocationService : IDisposable
    {
        IEnumerable<Location> GetLocations();
        Location GetLocationById(int locationId);
        void InsertLocation(Location location);
        void UpdateLocation(Location location);
        void DeactiveLocation(Location location);
        void Save();
        List<Location> GetAssignedLocations(int userId);
        bool LocationExists(int locationId);



    }
}
