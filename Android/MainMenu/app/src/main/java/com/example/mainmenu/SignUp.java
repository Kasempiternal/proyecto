package com.example.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mainmenu.Users.Menu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    Button remove;
    Button ok;
    EditText useremail,username,userpass;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        this.remove = findViewById(R.id.remove);
        this.ok = findViewById(R.id.ok);
        this.useremail = findViewById(R.id.email);
        this.username = findViewById(R.id.name);
        this.userpass = findViewById(R.id.pass);



        auth = FirebaseAuth.getInstance();

        //Clears all boxes

        remove.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                username.setText("");
                useremail.setText("");
                userpass.setText("");
            }
        });


        //Gets all boxes info to the strings to be used on another activity

        ok.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                //if something is empty, will prompt an error dialog
               if (useremail.toString().isEmpty() || username.toString().isEmpty() || userpass.toString().isEmpty()){
                    openErrorDialog();
               }
               else{

                   //Make everything string to be used on Register void and make sure password is atleast 5 characters long
                   String usernamest = username.getText().toString();
                   String useremailst = useremail.getText().toString();
                   String userpassst = userpass.getText().toString();


                   if(userpassst.length() < 5){
                       Toast.makeText(SignUp.this, "Password is very short. Must be longer than 5 characters.", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       register(useremailst,usernamest,userpassst);
                   }
               }
            }
        });
    }

    //opens error dialog
    public void openErrorDialog() {
        ErrorDialog ed = new ErrorDialog();
        ed.show(getSupportFragmentManager(), "");
    }


    //firebase creates the user with the given info
    private void register(final String useremail, final String username, String userpass){

        auth.createUserWithEmailAndPassword(useremail,userpass).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseuser = auth.getCurrentUser();
                            assert firebaseuser != null;

                            String userid = firebaseuser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference( "Users").child(userid);

                            HashMap<String, String> hashMap =  new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(SignUp.this, GroupChat.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("user", useremail);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUp.this,"You cant use this email or password", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }
}
