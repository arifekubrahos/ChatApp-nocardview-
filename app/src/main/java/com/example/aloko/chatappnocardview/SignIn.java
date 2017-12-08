package com.example.aloko.chatappnocardview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private EditText signEmail;
    private EditText signPass;
    private EditText signPassConf;
    private Button saveUser;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signEmail = (EditText) findViewById(R.id.signEmail);
        signPass = (EditText) findViewById(R.id.signPass);
        signPassConf = (EditText) findViewById(R.id.signPassConfirm);
        saveUser = (Button) findViewById(R.id.signButton);

        mAuth = FirebaseAuth.getInstance();

        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signPass.equals(signEmail)){
                    mAuth.createUserWithEmailAndPassword(signEmail.getText().toString(),signPass.getText().toString()).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(SignIn.this,LogIn.class);
                                startActivity(i);
                            }
                            else{
                                Log.i("error to save",task.getException().getMessage());
                            }
                        }
                    });
                }
                else{
                    //pasword match değil
                }
            }
        });
    }
}
