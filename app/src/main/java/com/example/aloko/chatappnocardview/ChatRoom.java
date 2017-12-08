package com.example.aloko.chatappnocardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {

    private ListView rooms;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference("Chats");
    private ArrayList<String> contentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        rooms = (ListView) findViewById(R.id.chatRoomList);
        contentList.add("yükleniyor...");

        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,contentList);

        rooms.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contentList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    contentList.add(ds.getKey());
                }
                adapter.notifyDataSetChanged();//liste her güncellendiğinde yenilenecek
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in= new Intent(getApplicationContext(),UserChat.class);
                in.putExtra("konu",contentList.get(i));
            }
        });
    }
}
