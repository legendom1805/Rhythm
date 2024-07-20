package com.example.rhythm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore fstore;
    TextView emailtext,usernametext;
    String userId;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        fstore = FirebaseFirestore.getInstance();
        usernametext = findViewById(R.id.usernameview);
        emailtext = findViewById(R.id.emailview2);

        userId = auth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("user").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                emailtext.setText(documentSnapshot.getString("Email"));
                usernametext.setText(documentSnapshot.getString("Username"));
            }
        });

        //Navigation Drawer
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;
        ImageButton drawernavtoggle;
        ImageButton acctoggle;

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        drawernavtoggle = findViewById(R.id.drawernavtoggle);
        acctoggle = findViewById(R.id.accounttogglle);
        setSupportActionBar(toolbar);
        account_fragment accountFragment = new account_fragment();

        acctoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,accountFragment).commit();
            }
        });

        drawernavtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if(item.getItemId()==R.id.account)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,accountFragment).commit();
                }
                else if (item.getItemId()==R.id.logout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent logint = new Intent(MainActivity.this, loginpage.class);
                    startActivity(logint);
                    finish();
                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });





        //Bottom navigation
        BottomNavigationView bottomNavigationView;
        home_fragment homeFragment = new home_fragment();
        search_fragment searchFragment = new search_fragment();
        mylibrary_fragment mylibraryFragment = new mylibrary_fragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,homeFragment).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.search) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,searchFragment).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.mymusic) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,mylibraryFragment).commit();
                    return true;
                }


                return false;
            }
        });

        user = auth.getCurrentUser();

        if(user==null)
        {
            Intent logint = new Intent(MainActivity.this, loginpage.class);
            startActivity(logint);
            finish();
        }

        else {

            Toast.makeText(MainActivity.this,"Welcome to Rhythm!",Toast.LENGTH_SHORT).show();



        }







    }

}

