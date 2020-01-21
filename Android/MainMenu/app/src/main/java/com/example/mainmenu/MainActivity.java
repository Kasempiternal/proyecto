package com.example.mainmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.*;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.mainmenu.Users.Menu;
import com.example.mainmenu.Users.Users;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    Button singup;
    Button login;
    EditText email;
    EditText pass;
    FirebaseAuth auth;

    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.login = (Button) findViewById(R.id.login);
        this.singup = (Button) findViewById(R.id.signup);
        this.email = (EditText) findViewById(R.id.emailtext);
        this.pass = (EditText) findViewById(R.id.passtext);

        auth = FirebaseAuth.getInstance();




        login.setEnabled(true);
        singup.setEnabled(true);

        final FirebaseUser curUser = auth.getInstance().getCurrentUser();



            login.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    String emailst = email.getText().toString();
                    String passst = pass.getText().toString();

                    if (emailst.isEmpty() || passst.isEmpty()) {
                        openErrorDialog();
                    } else {
                        auth.signInWithEmailAndPassword(emailst, passst).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(MainActivity.this, GroupChat.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Authentication Failed! Try again or use another acc.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }
            });

            singup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSignup();

                }
            });


        if (curUser != null) {
            Intent intent = new Intent(MainActivity.this, GroupChat.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }else{

        }
    }


    public void openSignup(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void openErrorDialog() {
        ErrorDialog ed = new ErrorDialog();
        ed.show(getSupportFragmentManager(), "");
    }


}
