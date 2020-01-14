package com.example.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mainmenu.Users.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.PrintStream;

public class Register extends AppCompatActivity {


    EditText email,username,pass;
    DatabaseReference reference;
    FirebaseAuth auth;
    ProgressBar pb;

    Button ok,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        pb = findViewById(R.id.pb);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        ok = findViewById(R.id.ok);
        clear = findViewById(R.id.remove);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser(v);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

    }

    public void RegisterUser(View v){
        pb.setVisibility(View.VISIBLE);
        final String emailt = email.getText().toString();
        final String namet = username.getText().toString();
        final String passt = pass.getText().toString();

        Users u = new Users();

        if(!emailt.equals("") && !passt.equals("") && !namet.equals("")){
            auth.createUserWithEmailAndPassword(emailt,passt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //create user on database

                        FirebaseUser user = auth.getCurrentUser();
                        Users u = new Users();
                        u.setName(namet);
                        u.setEmail(emailt);

                        reference.child(user.getUid()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    pb.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),"Created",Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent i = new Intent(Register.this,GroupChat.class);
                                    startActivity(i);
                                }
                                else{
                                    pb.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),"User could not be created",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                }
            }
            });
        }


    }



    public void login(View v){
        Intent i = new Intent(Register.this,MainActivity.class);
            startActivity(i);

    }
}
