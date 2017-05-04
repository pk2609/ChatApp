package com.appexample.chatapp;


import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    LinearLayout layout;
    Button sendButton;
    EditText etText;
    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private List<ChatAdd> mChats;
    Firebase reference1, reference2;
    String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

     //   layout = (LinearLayout)findViewById(R.id.layout1);
        sendButton = (Button) findViewById(R.id.btSent);
        etText = (EditText)findViewById(R.id.etText);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvChat);
        mChats = new ArrayList<>();

        mId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChatAdapter(mChats, mId);
        mRecyclerView.setAdapter(mAdapter);

        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://chatapp-e2f7e.firebaseio.com/messages/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://chatapp-e2f7e.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.username);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = etText.getText().toString();

                if (!messageText.equals("")){
                    //Map<String, String> map = new HashMap<String, String>();
                    //map.put("message", messageText);
                    //map.put("user", UserDetails.username);
                    //reference1.push().setValue(map);
                    //reference2.push().setValue(map);

                    reference1.push().setValue(new ChatAdd(messageText, mId));
                    reference2.push().setValue(new ChatAdd(messageText, mId));
                }

                etText.setText("");
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Map map = dataSnapshot.getValue(Map.class);
                //String message = map.get("message").toString();
                //String userName = map.get("user").toString();
                    if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                        try{

                            ChatAdd model = dataSnapshot.getValue(ChatAdd.class);

                            mChats.add(model);
                            mRecyclerView.scrollToPosition(mChats.size() - 1);
                            mAdapter.notifyItemInserted(mChats.size() - 1);

                        } catch (Exception ex) {
                            Log.e(TAG, ex.getMessage());
                        }
                    }
           //     if (userName.equals(UserDetails.username)){
           //         addMessageBox("You:-\n" + message, 1);
           //     }
           //     else{
           //         addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
           //     }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    //public void addMessageBox(String message ,int type){

    //    TextView textView = new TextView(this);
    //    textView.setText(message);
    //    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    //    lp.setMargins(0, 0, 0, 10);
    //    textView.setLayoutParams(lp);

    //    if (type == 1){
    //        textView.setBackgroundResource(R.drawable.rounded_corner1);
    //    }
    //    else {
    //        textView.setBackgroundResource(R.drawable.rounded_corner2);
    //    }

    //    layout.addView(textView);
    //    scrollView.fullScroll(View.FOCUS_DOWN);
    //}
}
