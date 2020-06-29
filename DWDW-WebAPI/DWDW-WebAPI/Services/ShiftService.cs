using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface IShiftService
    {
        List<ShiftViewModel> GetAllAdminShifts();
        Shift GetIDShift(int id);
        void CreateShift(ShiftPostPutModel sm);
        void UpdateShift(Shift shift, ShiftPostPutModel sm);
        List<Shift> GetManagerShift(int accountID);
        List<Shift> GetWorkerShift(int accountID);
        void Save();
        bool validateCreate(int userID, int? shiftUserLocationID);
        bool validateCreateRoom(int? roomID, int? userLocationID);
        bool validateBelongManager(int userID, int? userLocationID);
        void DeleteShift(Shift shift);
    }
    public class ShiftService : IShiftService
    {
        private DWDBContext db;

        public ShiftService()
        {
            this.db = new DWDBContext();
            db.Configuration.ProxyCreationEnabled = false;
        }

        public List<ShiftViewModel> GetAllAdminShifts()
        {
            var result = db.Shifts.Select(x => new ShiftViewModel
            {
                shiftId = x.shiftId,
                userLocationId = x.userLocationId,
                startDate = x.startDate,
                endDate = x.endDate,
                roomId = x.roomId,
                shiftType = x.shiftType,
                isActive = x.isActive
            }).ToList();
            return result;
        }

        public Shift GetIDShift(int id)
        {
            return db.Shifts.Find(id);
        }

        public List<Shift> GetManagerShift(int accountID)
        {
            var locationManager = db.UserLocations.Where(x => x.userId == accountID).ToList();
            List<int?> qualifyLocation = new List<int?>();
            List<int?> qualifyUserLocation = new List<int?>();
            for (int i = 0; i < locationManager.Count; i++)
            {
                int? a = locationManager.ElementAt(i).locationId;
                qualifyLocation.Add(a);
            }

            var userLocationAssigned = db.UserLocations.Where(x => qualifyLocation.Contains(x.locationId)).ToList();
            for (int i = 0; i < userLocationAssigned.Count; i++)
            {
                int? a = userLocationAssigned.ElementAt(i).userLocationId;
                qualifyUserLocation.Add(a);
            }


            //var shiftAssigned = db.Shifts.Where(x => x.userLocationId.con)
            var shiftAssigned = db.Shifts.Where(x => qualifyUserLocation.Contains(x.userLocationId)).ToList();
            return shiftAssigned;
        }

        public List<Shift> GetWorkerShift(int accountID)
        {
            List<int?> qualifyUserLocation = new List<int?>();
            var userLocation = db.UserLocations.Where(x => x.userId == accountID).ToList();
            for (int i = 0; i < userLocation.Count; i++)
            {
                int? a = userLocation.ElementAt(i).userLocationId;
                qualifyUserLocation.Add(a);
            }
            var workerShift = db.Shifts.Where(x => qualifyUserLocation.Contains(x.userLocationId)).ToList();
            return workerShift;
        }

        public void CreateShift(ShiftPostPutModel sm)
        {
            var shifts = db.Shifts;
            var s = shifts.Add(new Shift()
            {
                userLocationId = sm.userLocationId,
                startDate = sm.startDate,
                endDate = sm.endDate,
                roomId = sm.roomId,
                shiftType = sm.shiftType,
                isActive = sm.isActive
            });
        }

        public void UpdateShift(Shift shift, ShiftPostPutModel sm)
        {
            shift.userLocationId = sm.userLocationId;
            shift.startDate = sm.startDate;
            shift.endDate = sm.endDate;
            shift.roomId = sm.roomId;
            shift.shiftType = sm.shiftType;
            shift.isActive = sm.isActive;
        }

        public void Save()
        {
            db.SaveChanges();
        }


        //Check to see if the userLocationId input is qualify for the current manager
        public bool validateCreate(int userID, int? shiftUserLocationID)
        {
            bool allowed = false;
            var userLocation = db.UserLocations.Where(x => x.userId == userID).ToList();

            List<int?> qualifyLocation = new List<int?>();
            List<int?> qualifyUserLocation = new List<int?>();
            for (int i = 0; i < userLocation.Count; i++)
            {
                int? a = userLocation.ElementAt(i).locationId;
                qualifyLocation.Add(a);
            }

            var userLocationAssigned = db.UserLocations.Where(x => qualifyLocation.Contains(x.locationId)).ToList();
            for (int i = 0; i < userLocationAssigned.Count; i++)
            {
                int? a = userLocationAssigned.ElementAt(i).userLocationId;
                qualifyUserLocation.Add(a);
            }
            allowed = qualifyUserLocation.Contains(shiftUserLocationID);
            return allowed;
        }

        //Check to see if the room id is belong to the location
        public bool validateCreateRoom(int? roomID, int? userLocationID)
        {
            bool allowed = false;
            var exist = db.UserLocations.FirstOrDefault(x => x.userLocationId == userLocationID);
            var location = db.Locations.FirstOrDefault(x => x.locationId == exist.locationId);
            var roomList = db.Rooms.Where(x => x.locationId == location.locationId).ToList();
            if (roomList.Any(x => x.roomId == roomID))
            {
                allowed = true;
            }
            return allowed;
        }

        //Validate to see if shift belong to the manager
        public bool validateBelongManager(int userID, int? userLocationID)
        {
            bool allowed = false;
            var userLocation = db.UserLocations.FirstOrDefault(x => x.userLocationId == userLocationID);
            if (userID == userLocation.userId)
            {
                allowed = true;
            }
            return allowed;
        }

        public void DeleteShift(Shift shift)
        {
            db.Shifts.Remove(shift);
        }
    }
}