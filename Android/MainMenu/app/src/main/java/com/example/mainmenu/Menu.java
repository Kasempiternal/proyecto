package com.example.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mainmenu.CreateDialog;
import com.example.mainmenu.JoinDialog;
import com.example.mainmenu.Principal;
import com.example.mainmenu.R;
import com.example.mainmenu.Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class Menu extends AppCompatActivity {

    private Button join;
    private Button create;

    FirebaseAuth auth;
    DatabaseReference reference;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        uid = FirebaseAuth.getInstance().getUid();

        //String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        verifyUserLoggedin();

        TextView usertext = findViewById(R.id.usertext);

        //usertext.setText(user);

        this.join = findViewById(R.id.join);
        this.create = findViewById(R.id.create);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopupJoin();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopupCreate();
            }
        });


    }


    public void openPopupJoin() {
        JoinDialog joinDialog = new JoinDialog();
        joinDialog.show(getSupportFragmentManager(), "join");
    }

    public void openPopupCreate() {
        CreateDialog createDialog = new CreateDialog();
        createDialog.show(getSupportFragmentManager(), "create");
    }

    private void verifyUserLoggedin() {
        if (uid == null) {
            Intent intent = new Intent(this, Register.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out: {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
