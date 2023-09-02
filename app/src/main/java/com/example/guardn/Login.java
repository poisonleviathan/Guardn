package com.example.guardn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Login extends AppCompatActivity {
    EditText SigninEmail, SigninPass;
    Button SignUpRedirectBtn, SignInBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth= FirebaseAuth.getInstance();
        SigninEmail = findViewById(R.id.Signin_Email);
        SigninPass = findViewById(R.id.Signin_Pass);
        SignInBtn = findViewById(R.id.SignIn_Btn);
        SignUpRedirectBtn = findViewById(R.id.SignUpRedirect_btn);

        SignInBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = SigninEmail.getText().toString();
                String password = SigninPass.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Login.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Sign up Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        SigninPass.setError("Password Cannot be Empty");
                    }
                }else if(email.isEmpty()) {

                    SigninEmail.setError("Email Cannot be Empty");
                } else {
                    SigninEmail.setError("Please Enter Valid Email");

                }

            }

            });

        SignUpRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });

            }

    }
