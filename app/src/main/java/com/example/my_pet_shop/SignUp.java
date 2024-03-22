package com.example.my_pet_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button signup_btn, signin_btn;

  TextInputLayout phonenum,username,fullname,emailEditText, passwordEditText;
    private FirebaseAuth mAuth;
   FirebaseDatabase rootnode;
   DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        phonenum=findViewById(R.id.phonenum);
        username=findViewById(R.id.username);
        fullname=findViewById(R.id.fullname);


        signin_btn = findViewById(R.id.signin_btn);
        signup_btn = findViewById(R.id.signup_btn);
        emailEditText= findViewById(R.id.email);
        passwordEditText= findViewById(R.id.password);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();





            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

    }

    private void userRegister() {
        String email = emailEditText.getEditText().getText().toString().trim();
        String password = passwordEditText.getEditText().getText().toString().trim();

        // Checking validity
        if (email.isEmpty()) {
            emailEditText.setError("Enter an email address");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            emailEditText.setError("Enter a valid  email address");
            emailEditText.requestFocus();
            return;

        }

        if (password.isEmpty()) {
            passwordEditText.setError("Enter an email address");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {

            passwordEditText.setError("Minimum length of a password must be 6");
            passwordEditText.requestFocus();
            return;

        }


      mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful()) {
                    userRegisterRealtime();

                    Toast.makeText(getApplicationContext(),"Sign up is successfull",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);




                }

                else{

                    if(task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(getApplicationContext(), "User already signed up", Toast.LENGTH_SHORT).show();


                    }


                    else{
                        Toast.makeText(getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }



            }
        });



    }


    public void userRegisterRealtime(){



      rootnode=FirebaseDatabase.getInstance();
       reference= rootnode.getReference("Petbuddy/users");
       String name=fullname.getEditText().getText().toString().trim();
        String user=username.getEditText().getText().toString().trim();

        String phone=phonenum.getEditText().getText().toString().trim();
        String email=emailEditText.getEditText().getText().toString().trim();
        String password=passwordEditText.getEditText().getText().toString().trim();


        SignUpInformation information=new SignUpInformation(name,user,email,phone,password);




        // Create a child reference using the UID to store user data
        reference.child(phone).setValue(information);



    }




}


