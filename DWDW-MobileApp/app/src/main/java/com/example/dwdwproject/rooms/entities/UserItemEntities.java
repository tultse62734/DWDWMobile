package com.example.dwdwproject.rooms.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.utils.DataConvert;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity(tableName = "accounts")
public class UserItemEntities implements Serializable,Cloneable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid")
    @NonNull
    private String accountItemId;
    @TypeConverters(DataConvert.class)
    @SerializedName("user")
    private UserDTO mUserDTO;
    @SerializedName("token")
    private String token;
    @NonNull
    public String getAccountItemId() {
        return accountItemId;
    }

    public void setAccountItemId(@NonNull String accountItemId) {
        this.accountItemId = accountItemId;
    }

    public UserDTO getmUserDTO() {
        return mUserDTO;
    }

    public void setmUserDTO(UserDTO mUserDTO) {
        this.mUserDTO = mUserDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object clone() {
        UserItemEntities userItemEntities = new UserItemEntities();
        userItemEntities.setAccountItemId(accountItemId);
        userItemEntities.setmUserDTO(mUserDTO);
        userItemEntities.setToken(token);
        return userItemEntities;
    }
}
