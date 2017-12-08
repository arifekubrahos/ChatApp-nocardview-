package com.example.aloko.chatappnocardview;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserChat extends AppCompatActivity {

    private EditText senderMessage;
    private Button sendMessage;
    private ListView messages;
    CustomAdaptor adaptor;

    String content;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    FirebaseUser user;
    ArrayList<Message> messageArrayList  = new ArrayList<Message>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat);

        user= FirebaseAuth.getInstance().getCurrentUser();

        senderMessage = (EditText) findViewById(R.id.editText);
        sendMessage = (Button) findViewById(R.id.button);

        messages = (ListView) findViewById(R.id.chatList);

        messageArrayList.add(new Message());
        adaptor = new CustomAdaptor(this ,messageArrayList);
        messages.setAdapter(adaptor);

        //activityler arası data geçişi için bundlde kullanılır bir activitydeki daki data başka bir activity e geçiş
        Bundle bundle = getIntent().getExtras();//herhangi bi activityde ekstra başka yerlerde kullanılmak istenen exstralar

        if(bundle != null){
            content = bundle.getString("konu");//sohbet odalarının konularını almak için
            reference = db.getReference("Chats/"+content+"/mesaj");
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageArrayList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    messageArrayList.add(ds.getValue(Message.class));

                }
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = senderMessage.getText().toString();
                    //SimpleDateFormat df=

            }
        });

    }
}
