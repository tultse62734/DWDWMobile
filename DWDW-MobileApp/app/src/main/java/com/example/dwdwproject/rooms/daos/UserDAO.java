package com.example.dwdwproject.rooms.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dwdwproject.rooms.entities.UserItemEntities;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertAccount(UserItemEntities... userItemEntities);
    @Query("DELETE FROM accounts")
    void deleleAllAccount();
    @Delete
    void deleteAccount(UserItemEntities... userItemEntities);
    @Update
    void updateAccount(UserItemEntities... userItemEntities);

    @Query("select * from accounts")
    LiveData<UserItemEntities> getAccount();
    @Query("select token from accounts")
    List<String> getAccessToken();
    @Query("select * from accounts")
    UserItemEntities getAccountEntities();

}
