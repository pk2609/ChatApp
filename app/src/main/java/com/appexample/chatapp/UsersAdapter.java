package com.appexample.chatapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.myviewholder>{

    private List<UsersList> UsersName;
    private Context context;

    public class myviewholder extends RecyclerView.ViewHolder {

        LinearLayout layout1;
        public TextView Users, LastText, Time;
        public ImageView img1, img2;

        public myviewholder(View itemView) {
            super(itemView);

            Users = (TextView) itemView.findViewById(R.id.Users);
            LastText = (TextView) itemView.findViewById(R.id.last_text);
            Time = (TextView) itemView.findViewById(R.id.time);
            layout1 = (LinearLayout) itemView.findViewById(R.id.layout1);
            img1 = (ImageView) itemView.findViewById(R.id.img_1);
            img2 = (ImageView) itemView.findViewById(R.id.img_2);

        }
    }

    public UsersAdapter(Context context, List<UsersList> UsersName) {
        this.UsersName = UsersName;
        this.context = context;
    }

    @Override
    public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatuserlist, parent, false);

        return new myviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(myviewholder holder, int position) {
        final UsersList MyUser = UsersName.get(position);
        holder.Users.setText(MyUser.getUsers());
        holder.LastText.setText(MyUser.getLastChat());
        holder.Time.setText(MyUser.getTime());
        Glide.with(context).load("").placeholder(MyUser.img_1).into(holder.img1);
        Glide.with(context).load("").placeholder(MyUser.img_1).into(holder.img2);
        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chat.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return UsersName.size();
    }
}