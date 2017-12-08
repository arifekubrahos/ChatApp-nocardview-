package com.example.aloko.chatappnocardview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;

    private Button logButton;
    private EditText logEmail;
    private EditText logPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logButton= (Button) findViewById(R.id.logButton);
        logEmail = (EditText) findViewById(R.id.logEmail);
        logPass = (EditText) findViewById(R.id.logPass);

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();

        if(fUser != null)
        {
            Intent i = new Intent(this,ChatRoom.class);
            startActivity(i);
            finish();
        }


        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(logEmail.getText().toString().trim(),logPass.toString().trim()).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(getApplicationContext(),ChatRoom.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signText:
                Intent i = new Intent(LogIn.this,SignIn.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
