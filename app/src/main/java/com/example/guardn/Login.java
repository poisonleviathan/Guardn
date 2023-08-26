package com.example.guardn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText SigninEmail, SigninPass;
    Button SignUpRedirectBtn, SignInBtn;

    FirebaseDatabase database;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SigninEmail = findViewById(R.id.Signin_Email);
        SigninPass = findViewById(R.id.Signin_Pass);
        SignInBtn = findViewById(R.id.SignIn_Btn);
        SignUpRedirectBtn = findViewById(R.id.SignUpRedirect_btn);

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()){

                }else{
                    checkUser();
                }
            }
        });
        SignUpRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateEmail(){
        String val = SigninEmail.getText().toString();
        if(val.isEmpty()){
            SigninEmail.setError("Email Cannot be Empty");
            return false;

        }else {
            SigninEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = SigninPass.getText().toString();
        if(val.isEmpty()){
            SigninPass.setError("Password Cannot be Empty");
            return false;

        }else {
            SigninPass.setError(null);
            return true;
        }
    }

    public void checkUser () {
        String userEmail = SigninEmail.getText().toString().trim();
        String userPassword =SigninPass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(userEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    SigninEmail.setError(null);
                    String passwordFromDB = snapshot.child(userEmail).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB,userPassword)){
                        SigninEmail.setError(null);
                        Intent intent =new Intent(Login.this, MainActivity.class);
                        startActivity(intent);

                    }else {
                        SigninPass.setError("Invalid Credentials");
                        SigninPass.requestFocus();

                    }

                } else {
                    SigninEmail.setError("User Does Not Exist");
                    SigninEmail.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}