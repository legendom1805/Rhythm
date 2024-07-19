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
            Intent logint = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logint);
        }
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signuppage);

        mAuth = FirebaseAuth.getInstance();
        TextInputEditText emailsptext, passwordsptext;
        Button signbtn;

        emailsptext = findViewById(R.id.emailaddresssp);
        passwordsptext = findViewById(R.id.passwordsp);

        signbtn = findViewById(R.id.signupbtn);
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password ;
                email = String.valueOf(emailsptext.getText());
                password = String.valueOf(passwordsptext.getText());


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signuppage.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signuppage.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(Signuppage.this, "Password must have atleast 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }



                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signuppage.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    Intent logint = new Intent(getApplicationContext(), loginpage.class);
                                    startActivity(logint);
                                    finish();
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Signuppage.this, "Error Occurred!" + task.getException().getMessage(),
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
            }
        });
    }
}



