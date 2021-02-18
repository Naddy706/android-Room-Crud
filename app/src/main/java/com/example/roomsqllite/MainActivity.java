package com.example.roomsqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import java.util.List;
import android.app.TaskInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText task;
    Button btnAdd;

    RecyclerView recyclerView;

    List<Tasks>  datalist = new ArrayList<>();


    LinearLayoutManager linearLayoutManager;
    RoomDb roomDatabase;

    TaskAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.task);
        btnAdd = findViewById(R.id.add);



        recyclerView = findViewById(R.id.recycler);


        roomDatabase = RoomDb.getInstance(MainActivity.this);
        datalist = roomDatabase.taskDao().getALL();

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new TaskAdapter(datalist,MainActivity.this);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stext = task.getText().toString().trim();
                if(stext.isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter task", Toast.LENGTH_SHORT).show();
                }

                Tasks tas= new Tasks();
                tas.setText(stext);

                roomDatabase.taskDao().insert(tas);

                task.setText("");
                datalist.clear();
                datalist.addAll(roomDatabase.taskDao().getALL());
                adapter.notifyDataSetChanged();
            }
        });






    }
}