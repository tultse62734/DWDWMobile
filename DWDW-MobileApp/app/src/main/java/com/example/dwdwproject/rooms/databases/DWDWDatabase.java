package com.example.dwdwproject.rooms.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import static com.example.dwdwproject.rooms.databases.DWDWDatabase.DATABASE_VERSION;

import com.example.dwdwproject.rooms.daos.UserDAO;
import com.example.dwdwproject.rooms.entities.UserItemEntities;

@Database(entities ={ UserItemEntities.class}, exportSchema = false, version = DATABASE_VERSION)
public  abstract class DWDWDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "dwdw-database";
    public static final int DATABASE_VERSION = 1;
    private static DWDWDatabase INSTANCE;
    public abstract UserDAO userDAO();

    public static DWDWDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DWDWDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DWDWDatabase.class, DATABASE_NAME)
                            .build();
                }
            }

        }
        return INSTANCE;
    }

}
