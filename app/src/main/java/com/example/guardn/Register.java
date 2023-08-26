package com.example.guardn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class Register extends AppCompatActivity {

    EditText SignUpUname, SignUpEmail, SignUpName, SignUpPass;
    Button LoginRedirectbtn, SignUpbtn;

    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        SignUpName = findViewById(R.id.SignUp_Name);
        SignUpEmail = findViewById(R.id.SignUp_Email);
        SignUpUname = findViewById(R.id.SignUp_Uname);
        SignUpPass = findViewById(R.id.SignUp_Pass);
        SignUpbtn = findViewById(R.id.SignUp_btn);
        LoginRedirectbtn = findViewById(R.id.LoginRedirect_btn);

        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                database= FirebaseDatabase.getInstance();
                reference= database.getReference("users");

                String name = SignUpName.getText().toString();
                String email = SignUpEmail.getText().toString();
                String username = SignUpUname.getText().toString();
                String password = SignUpPass.getText().toString();

                HelperClass helperClass =new HelperClass (name,email,username,password);
                reference.child(name).setValue(helperClass);

               Toast.makeText(Register.this,"You Have SignUp SucccesFully",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(Register.this, Login.class);
                startActivity(intent);

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