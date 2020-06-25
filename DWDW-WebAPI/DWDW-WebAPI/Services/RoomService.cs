using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Transactions;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface IRoomService : IDisposable
    {
        List<Room> GetRooms();
        Room GetRoomById(int roomId);
        bool InsertRoom(Room room);
        bool UpdateRoom(Room room);
        bool DeactiveRoom(Room room);
        List<Room> GetRoomsByLocationId(int locationId);
        bool RoomExists(int roomId);
    }
    public class RoomService : IRoomService, IDisposable
    {
        private readonly DWDBContext context;
        private bool disposed = false;

        public RoomService(DWDBContext context)
        {
            this.context = context;
        }
        public bool DeactiveRoom(Room room)
        {
            try
            {
                using (TransactionScope scope = new TransactionScope(TransactionScopeOption.RequiresNew))
                {
                    room.isActive = false;
                    context.Entry(room).State = EntityState.Modified;
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

        public List<Room> GetRooms()
        {
            return context.Rooms.ToList();
        }

        public bool InsertRoom(Room room)
        {
            if (context.Locations.Find(room.locationId) == null) return false;
            try
            {
                using (TransactionScope scope = new TransactionScope(TransactionScopeOption.RequiresNew))
                {
                    context.Rooms.Add(room);
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


        public List<Room> GetRoomsByLocationId(int locationId)
        {
            return context.Rooms.Where(r => r.locationId == locationId).ToList();
        }

        public bool UpdateRoom(Room room)
        {
            try
            {
                using (TransactionScope scope = new TransactionScope(TransactionScopeOption.RequiresNew))
                {
                    context.Entry(room).State = EntityState.Modified;
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

        public bool RoomExists(int roomId)
        {
            return context.Rooms.Count(e => e.roomId == roomId) > 0;
        }
    }
}