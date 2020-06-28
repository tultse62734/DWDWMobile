using DWDW_WebAPI.Firebase;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Script.Serialization;

namespace DWDW_WebAPI.Services
{
    public interface IRecordService
    {
        List<RecordViewModel> GetAllAdminRecords();
        Record GetIDRecord(int id);
        void CreateRecord(RecordPostModel rm);
        bool validateRecord(int managerID, RecordViewModel record);
        void sendNotify(int deviceID);
        void Save();
    }
    public class RecordService : IRecordService
    {
        private DWDBContext db;

        public RecordService()
        {
            this.db = new DWDBContext();
            db.Configuration.ProxyCreationEnabled = false;
        }
        public List<RecordViewModel> GetAllAdminRecords()
        {
            var result = db.Records.Select(x => new RecordViewModel
            {
                recordId = x.recordId,
                deviceId = x.deviceId,
                recordDate = x.recordDate,
                image = x.image,
                recordStatus = x.recordStatus,
                isActive = x.isActive
            }).ToList();
            return result;
        }

        public Record GetIDRecord(int id)
        {
            return db.Records.Find(id);
        }

        public void CreateRecord(RecordPostModel rm)
        {
            var record = db.Records;
            var r = record.Add(new Record()
            {
                deviceId = rm.deviceId,
                recordDate = rm.recordDate,
                image = rm.image,
                recordStatus = rm.recordStatus,
                isActive = rm.isActive
            });
        }

        //Check if this record belong to the manager
        public bool validateRecord(int managerID, RecordViewModel record)
        {
            bool result = false;
            var roomDeviceList = db.RoomDevices.Where(x => x.deviceId == record.deviceId
            && x.endDate > record.recordDate).ToList();
            if (roomDeviceList.Count != 0)
            {
                //Lay ra ngay thang gan nhat
                var closesDate = roomDeviceList.Min(a => a.endDate);
                var roomDevice = roomDeviceList.FirstOrDefault(x => x.endDate == closesDate);
                var room = db.Rooms.Find(roomDevice.roomId);
                int? locationID = room.locationId;

                var locationUser = db.UserLocations.Where(x => x.locationId == locationID).ToList();
                var locationManager = locationUser.FirstOrDefault(x => x.userId == managerID);
                if (locationManager != null)
                {
                    result = true;
                }
            }
            return result;
        }

        public void sendNotify(int deviceID)
        {
            string timeNow = DateTime.Now.ToString("H:mm");

            //Tu device get ra manager
            var roomDevice = db.RoomDevices.FirstOrDefault(x => x.deviceId == deviceID
            && x.endDate > DateTime.Now && x.isActive == true);
            var room = db.Rooms.Find(roomDevice.roomId);

            var userListRelated = db.Users.Where(a => a.UserLocations.Any(b => b.locationId == room.locationId))
                .ToList();
            var manager = userListRelated.FirstOrDefault(x => x.roleId == 2);
            string tokenDevice = manager.deviceToken;
            string titleText = "Detect drowsiness!";
            string bodyText = "There was a drowsiness in " + room.roomCode + " at " + timeNow;

            var messageInformation = new Message()
            {
                notification = new Notification()
                {
                    title = titleText,
                    body = bodyText
                },
                to = tokenDevice
            };
            var serializer = new JavaScriptSerializer();
            var json = serializer.Serialize(messageInformation);
            byte[] byteArray = Encoding.UTF8.GetBytes(json);
            FirebaseNotification firebaseNotification = new FirebaseNotification();
            firebaseNotification.SendNotification(byteArray);
        }

        public void Save()
        {
            db.SaveChanges();
        }
    }
}