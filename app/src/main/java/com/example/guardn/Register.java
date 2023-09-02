package com.example.guardn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class Register extends AppCompatActivity {

private FirebaseAuth auth;
    EditText SignUpUname, SignUpEmail, SignUpName, SignUpPass;
    Button LoginRedirectbtn, SignUpbtn;

    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        SignUpName = findViewById(R.id.SignUp_Name);
        SignUpEmail = findViewById(R.id.SignUp_Email);
        SignUpUname = findViewById(R.id.SignUp_Uname);
        SignUpPass = findViewById(R.id.SignUp_Pass);
        SignUpbtn = findViewById(R.id.SignUp_btn);
        LoginRedirectbtn = findViewById(R.id.LoginRedirect_btn);

        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = SignUpEmail.getText().toString().trim();
                String password = SignUpPass.getText().toString().trim();

                if (user.isEmpty()) {
                    SignUpEmail.setError("Email Cannot be empty");

                }
                if (password.isEmpty()) {
                    SignUpPass.setError("Email Cannot be empty");

                } else {
                    auth.createUserWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Signup Successful ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            } else {
                                Toast.makeText(Register.this, "Sign up Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
                LoginRedirectbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(Register.this, Login.class);
                        startActivity(intent);

                    }
                });

            }






    }
