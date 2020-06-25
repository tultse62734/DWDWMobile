using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.ViewModel
{
    public class ModelMapping
    { 
        public Room CreateRoomMapping(RoomViewModel viewModel)
        {
            return new Room()
            {
                roomId = viewModel.roomId,
                roomCode = viewModel.roomCode,
                locationId = viewModel.locationId,
                isActive = viewModel.isActive
            };
        }
    public void UpdateRoomMapping(RoomViewModel viewModel, Room model)
        {
            model.roomId = viewModel.roomId;
            model.roomCode = viewModel.roomCode;
            model.locationId = viewModel.locationId;
            model.isActive = viewModel.isActive;
        }
        public User CreateUserMapping(UserViewModel viewModel)
        {
            return new User()
            {
                userId = viewModel.userId,
                userName = viewModel.userName,
                password = viewModel.password,
                phone = viewModel.phone,
                dateOfBirth = viewModel.dateOfBirth,
                gender = viewModel.gender,
                deviceToken = viewModel.deviceToken,
                roleId = viewModel.roleId,
                isActive = viewModel.isActive
            };
        }
        public void UpdateUserMapping(UserViewModel viewModel, User model)
        {
            model.userId = viewModel.userId;
            model.userName = viewModel.userName;
            model.password = viewModel.password;
            model.phone = viewModel.phone;
            model.dateOfBirth = viewModel.dateOfBirth;
            model.gender = viewModel.gender;
            model.deviceToken = viewModel.deviceToken;
            model.roleId = viewModel.roleId;
            model.isActive = viewModel.isActive;
        }
        public Location CreateLocationMapping(LocationViewModel viewModel)
        {
            return new Location()
            {
                locationId = viewModel.locationId,
                locationCode = viewModel.locationCode,
                isActive = viewModel.isActive
            };
        }
        public void UpdateLocationMapping(LocationViewModel viewModel, Location model)
        {
            model.locationId = viewModel.locationId;
            model.locationCode = viewModel.locationCode;
            model.isActive = viewModel.isActive;
        }
    }
}