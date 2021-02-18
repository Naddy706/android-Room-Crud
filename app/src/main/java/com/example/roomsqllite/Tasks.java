package com.example.roomsqllite;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tasks")
public class Tasks implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo(name = "text")
    public String text;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
