package com.example.rhythm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class loginpage extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent logint = new Intent(loginpage.this, MainActivity.class);
            startActivity(logint);
            finish();
        }
    }
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);
        mAuth = FirebaseAuth.getInstance();
        TextInputEditText emaillogin,passwordlogin;
        Button login;
        emaillogin = findViewById(R.id.emaillogin);
        passwordlogin = findViewById(R.id.passwordlogin);
        login = findViewById(R.id.loginbtn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emaillogintext, passwordlogintext;

                emaillogintext = String.valueOf(emaillogin.getText());
                passwordlogintext = String.valueOf(passwordlogin.getText());

                if (TextUtils.isEmpty(emaillogintext)) {
                    Toast.makeText(loginpage.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwordlogintext)) {
                    Toast.makeText(loginpage.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emaillogintext, passwordlogintext)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(loginpage.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                                    Intent logint = new Intent(loginpage.this, MainActivity.class);
                                    startActivity(logint);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(loginpage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        TextView text;
        text = findViewById(R.id.textView2);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup;
                signup = new Intent(loginpage.this, Signuppage.class);
                startActivity(signup);
                finish();

            }
        });



    }
}