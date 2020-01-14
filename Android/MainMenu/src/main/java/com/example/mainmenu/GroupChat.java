package com.example.mainmenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mainmenu.Users.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroupChat extends AppCompatActivity implements View.OnClickListener{


    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MessageAdapter messageAdapter;
    Users user;
    List<Message> messages;

    RecyclerView recyclerView;
    EditText messagetxt;
    ImageButton imageButton,photobt;

    String checker, myurl="";
    StorageTask upload;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        databaseReference = firebaseDatabase.getInstance().getReference().child("messages");
        initialize();
    }

    private void initialize() {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = new Users();

        recyclerView = findViewById(R.id.recycleview);
        messagetxt = findViewById(R.id.messagebox);
        imageButton = findViewById(R.id.sendbutton);
        imageButton.setOnClickListener(this);
        messages = new ArrayList<>();

        photobt = findViewById(R.id.photo);

        photobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[] = new CharSequence[]{
                        "Images",
                        "PDF Files",
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupChat.this);
                builder.setTitle("Select the file");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            checker = "image";

                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent,"Select image"), 438);
                        }
                        if(which == 1){
                            checker= "pdf";
                        }
                    }
                });
            }
        });

    }




    @Override
    public void onClick(View v) {

        if(!TextUtils.isEmpty(messagetxt.getText().toString())){
            Message message = new Message(messagetxt.getText().toString(),user.getName());
            messagetxt.setText("");
            databaseReference.push().setValue(message);

        }
        else{
            Toast.makeText(getApplicationContext(), "Blank message cant be sent",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.sign_out){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(GroupChat.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseUser curUser = firebaseAuth.getInstance().getCurrentUser();

        if (curUser != null) {
            user.setUid(curUser.getUid());
            user.setEmail(curUser.getEmail());
            user.setName(curUser.getDisplayName());


        firebaseDatabase.getReference("Users").child(curUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(Users.class);
                user.setUid(curUser.getUid());
                AllMethods.name = user.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Iterator iterator = dataSnapshot.getChildren().iterator();
                if (dataSnapshot.exists()) {
                    while (iterator.hasNext()) {
                        String mesage = (String) ((DataSnapshot) iterator.next()).getValue();
                        String name = (String) ((DataSnapshot) iterator.next()).getValue();

                        Message message = new Message();
                        message.setKey(dataSnapshot.getKey());
                        message.setMessage(mesage);
                        message.setName(name);
                        messages.add(message);
                        displayMessages(messages);
                     
                    }
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.exists()) {
                    Message message = dataSnapshot.getValue(Message.class);
                    message.setKey(dataSnapshot.getKey());

                    List<Message> newMessage = new ArrayList<>();

                    for (Message m : messages) {
                        if (m.getKey().equals(message.getKey())) {
                            newMessage.add(message);
                        } else {
                            newMessage.add(m);
                        }
                    }

                    messages = newMessage;
                    displayMessages(messages);
                }


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());

                List<Message> newMessages = new ArrayList<Message>();

                for (Message m : messages) {
                    if (!m.getKey().equals(message.getKey())) {
                        newMessages.add(message);
                    } else {

                    }
                }

                messages = newMessages;
                displayMessages(messages);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        else{
            Intent inten = new Intent(GroupChat.this,Register.class);
            startActivity(inten);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        messages = new ArrayList<>();

    }

    private void displayMessages(List<Message> messages){
        recyclerView.setLayoutManager(new LinearLayoutManager(GroupChat.this));
        messageAdapter = new MessageAdapter(GroupChat.this,messages,databaseReference);
        recyclerView.setAdapter(messageAdapter);
    }


}
