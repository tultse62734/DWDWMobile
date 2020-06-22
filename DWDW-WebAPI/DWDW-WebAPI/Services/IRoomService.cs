using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DWDW_WebAPI.Services
{
    public interface IRoomService : IDisposable
    {
        IEnumerable<Room> GetRooms();
        Room GetRoomById(int roomId);
        void InsertRoom(Room room);
        void DeactiveRoom(int roomId);
        void Save();
        List<Room> GetRoomsByLocationId(int locationId);
    }
}
