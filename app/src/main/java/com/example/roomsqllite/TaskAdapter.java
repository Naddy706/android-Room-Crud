package com.example.roomsqllite;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<Tasks> datalist;
    Context context;
    RoomDb database;

    public TaskAdapter(List<Tasks> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist,parent , false);
        return  new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Tasks data = datalist.get(position);
        database = RoomDb.getInstance(context);

        holder.text.setText(data.getText());
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tasks t = datalist.get(holder.getAdapterPosition());
                int sid= t.getId();
                String stext = t.getText();

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialoug_update);

                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);

                dialog.show();

                EditText edtext =dialog.findViewById(R.id.edit_text);

                Button update = dialog.findViewById(R.id.btn_update);

                edtext.setText(stext);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();


                        String etext= edtext.getText().toString().trim();

                        database.taskDao().update(sid,etext);
                        datalist.clear();
                        datalist.addAll(database.taskDao().getALL());
                        notifyDataSetChanged();

                    }
                });

            }
        });


        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasks ts = datalist.get(holder.getAdapterPosition());
                database.taskDao().Delete(ts);
                int ps= holder.getAdapterPosition();
                datalist.remove(ps);
                notifyItemRemoved(ps);
                notifyItemRangeChanged(ps,datalist.size());
            }
        });


    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        
        TextView text;
        ImageView up,del;

    public ViewHolder(View view){
        super(view);

        text = view.findViewById(R.id.tasks);
        up= view.findViewById(R.id.edit);
        del =view.findViewById(R.id.delete);

    }

}

}
