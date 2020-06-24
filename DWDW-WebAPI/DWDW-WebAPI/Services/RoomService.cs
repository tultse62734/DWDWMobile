using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

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
    public class RoomService : IRoomService, IDisposable
    {
        private DWDBContext context;
        private bool disposed = false;

        public RoomService(DWDBContext context)
        {
            this.context = context;
            context.Configuration.ProxyCreationEnabled = false;
        }
        public void DeactiveRoom(int roomId)
        {
            throw new NotImplementedException();
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

        public Room GetRoomById(int roomId)
        {
            return context.Rooms.Find(roomId);
        }

        public IEnumerable<Room> GetRooms()
        {
            return context.Rooms;
        }

        public void InsertRoom(Room room)
        {
            context.Rooms.Add(room);
        }

        public void Save()
        {
            context.SaveChanges();
        }

        public List<Room> GetRoomsByLocationId(int locationId)
        {
            return context.Rooms.Where(r => r.locationId == locationId).ToList();
        }
    }
}