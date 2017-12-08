package com.example.aloko.chatappnocardview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aloko on 9.07.2017.
 */

public class CustomAdaptor extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Message> messageArrayList;
    FirebaseUser firebaseUser;

    public CustomAdaptor(Activity activity,ArrayList<Message> messageArrayList,FirebaseUser firebaseUser){
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messageArrayList = messageArrayList;
        this.firebaseUser = firebaseUser;
    }

    public CustomAdaptor (UserChat userChat, ArrayList<Message> messagesList){

    }
    @Override
    public int getCount() {
        return messageArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View line;
        if(firebaseUser.getEmail().equalsIgnoreCase(messageArrayList.get(position).getSender())){
            line = inflater.inflate(R.layout.activity_right_line,null);
            TextView sender = line.findViewById(R.id.textViewSender);
            TextView content = line.findViewById(R.id.textViewContent);
            TextView history = line.findViewById(R.id.textViewHistoryRight);

            sender.setText(messageArrayList.get(position).getSender());
            content.setText(messageArrayList.get(position).getMessageText());
            history.setText(messageArrayList.get(position).getTime());
        }
        else{
            line = inflater.inflate(R.layout.activity_left_line,null);
            TextView sender = line.findViewById(R.id.textViewSender);
            TextView content = line.findViewById(R.id.textViewContent);
            TextView history = line.findViewById(R.id.textViewHistoryRight);

            sender.setText(messageArrayList.get(position).getSender());
            content.setText(messageArrayList.get(position).getMessageText());
            history.setText(messageArrayList.get(position).getTime());
        }

        return line;


    }
}
