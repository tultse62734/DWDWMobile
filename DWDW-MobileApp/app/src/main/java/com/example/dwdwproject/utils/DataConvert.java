package com.example.dwdwproject.utils;

import androidx.room.TypeConverter;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

public class DataConvert implements Serializable {
    @TypeConverter // note this annotation
    public String fromUserList(UserDTO1 userDTO) {
        if (userDTO == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<UserDTO1>() {
        }.getType();
        String json = gson.toJson(userDTO, type);
        return json;
    }

    @TypeConverter // note this annotation
    public UserDTO1 toExtraList(String useDTOString) {
        if (useDTOString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<UserDTO1>() {
        }.getType();
        UserDTO1 cardType = gson.fromJson(useDTOString, type);
        return cardType;
    }
}
