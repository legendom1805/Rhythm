package com.example.rhythm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Setting up toolbar as actionbar
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Firebase Authentication Logout
        FirebaseAuth auth;
        TextView email;
        Button logout;
        FirebaseUser user;

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailview);
        logout= findViewById(R.id.logoutbtn);
        user = auth.getCurrentUser();

        if(user==null)
        {
            Intent logint = new Intent(MainActivity.this, loginpage.class);
            startActivity(logint);
            finish();
        }

        else {

            email.setText(user.getEmail());

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent logint = new Intent(MainActivity.this, loginpage.class);
                startActivity(logint);
                finish();
            }
        });


    }
}