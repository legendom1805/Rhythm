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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class Signuppage extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent logint = new Intent(Signuppage.this, MainActivity.class);
            startActivity(logint);
            finish();
        }
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signuppage);

        mAuth = FirebaseAuth.getInstance();
        TextInputEditText usernamesptext, emailsptext, passwordsptext, repeatpsptext;
        Button signbtn;
        usernamesptext = findViewById(R.id.unamesp);
        emailsptext = findViewById(R.id.emailaddresssp);
        passwordsptext = findViewById(R.id.passwordsp);
        repeatpsptext = findViewById(R.id.passwordsp1);

        signbtn = findViewById(R.id.signupbtn);
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username, email, password, repeatpass;

                username = String.valueOf(usernamesptext.getText());
                email = String.valueOf(emailsptext.getText());
                password = String.valueOf(passwordsptext.getText());
                repeatpass = String.valueOf(repeatpsptext.getText());

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Signuppage.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signuppage.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signuppage.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(repeatpass)) {
                    Toast.makeText(Signuppage.this, "Repeat Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (repeatpass != password) {
                    Toast.makeText(Signuppage.this, "Re-entered Password doesn't match", Toast.LENGTH_SHORT).show();
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signuppage.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    Intent logint = new Intent(Signuppage.this, loginpage.class);
                                    startActivity(logint);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Signuppage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        TextView text;
        text = findViewById(R.id.loginintent);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login;
                login = new Intent(Signuppage.this, loginpage.class);
                startActivity(login);
                finish();
            }
        });
    }
}



