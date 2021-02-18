package com.example.roomsqllite;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tasks.class},version = 1,exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    public static RoomDb database;
    public static String Database_Name = "database";

    public synchronized static RoomDb getInstance(Context context){

        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),RoomDb.class,Database_Name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return database;
    }



    public abstract TaskDao taskDao();

}
