package com.appexample.chatapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Users extends AppCompatActivity {

    private List<UsersList> UsersList;
    private RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TextView noUsersText;
    int totalUsers = 0;
    ProgressDialog pd;
    Context context;
    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        noUsersText = (TextView) findViewById(R.id.noUsersText);linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);

        UsersList=new ArrayList<>();
        adapter=new UsersAdapter(this,UsersList);
        recyclerView.setAdapter(adapter);

        pd = new ProgressDialog(Users.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://chatapp-e2f7e.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                doOnSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("" + error);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Users.this);
        rQueue.add(request);

     //   usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     //       @Override
     //       public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
     //           UserDetails.chatWith = al.get(position);
     //           startActivity(new Intent(Users.this, Chat.class));
     //       }
     //   });
     }

    public void doOnSuccess(String s) {
        try {
            Toast.makeText(Users.this, "Success", Toast.LENGTH_SHORT).show();
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            UsersList=new ArrayList<>();
            while (i.hasNext()) {
                UsersList key=new UsersList();
                key.Users=i.next().toString();

                if (!key.equals(UserDetails.username)) {
                    UsersList.add(key);
                    Toast.makeText(Users.this, ""+key.Users, Toast.LENGTH_SHORT).show();

                    //adapter.notifyDataSetChanged();
                }
                totalUsers++;

            }

            adapter=new UsersAdapter(this,UsersList);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Failed.",e.getMessage());
        }
        if (totalUsers <= 1) {
            noUsersText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noUsersText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        }
        pd.dismiss();
    }
}