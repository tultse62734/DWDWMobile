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
    private int accountItemId;
    @TypeConverters(DataConvert.class)
    @SerializedName("user")
    private UserDTO user;
    @SerializedName("token")
    private String token;

    public int getAccountItemId() {
        return accountItemId;
    }

    public void setAccountItemId(int accountItemId) {
        this.accountItemId = accountItemId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
