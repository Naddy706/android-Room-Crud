package com.example.roomsqllite;

import android.icu.text.Replaceable;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Tasks... tasks);

    @Delete
    void Delete(Tasks... tasks);


    @Query("UPDATE tasks set text=:stext WHERE id =:Sid")
    void update(int Sid,String stext);

    @Query("SELECT * FROM tasks")
    List<Tasks> getALL();


}
