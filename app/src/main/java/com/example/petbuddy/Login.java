package com.example.petbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    LottieAnimationView laView;
    Button go_btn, forgetpass, signup;
    TextInputEditText emailEditText, passwordEditText, phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseFactory.getFirebaseAuth();
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        phoneEditText = findViewById(R.id.phoneEditText);

        go_btn = findViewById(R.id.go_btn);
        signup = findViewById(R.id.signup);

        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String phoneString = phoneEditText.getText().toString();

        if (email.isEmpty()) {
            emailEditText.setError("Enter an email address");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Enter a password");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Minimum length of a password must be 6");
            passwordEditText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sign in Successful", Toast.LENGTH_SHORT).show();
                    navigateToProfileAct(phoneString);
                } else {
                    Toast.makeText(getApplicationContext(), "Sign in Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void navigateToProfileAct(String phone) {
        DatabaseReference reference = FirebaseFactory.getDatabaseReference("Petbuddy/users");
        Query checkUserDatabase = reference.orderByChild("phone").equalTo(phone);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String emailFromDb = snapshot.child(phone).child("email").getValue(String.class);
                String passFromDb = snapshot.child(phone).child("password").getValue(String.class);
                String phoneFromDb = snapshot.child(phone).child("phone").getValue(String.class);
                String userNameFromDb = snapshot.child(phone).child("username").getValue(String.class);
                String nameFromDb = snapshot.child(phone).child("name").getValue(String.class);

                Intent intent = new Intent(Login.this, ProfileActivity.class);
                intent.putExtra("email", emailFromDb);
                intent.putExtra("password", passFromDb);
                intent.putExtra("phone", phoneFromDb);
                intent.putExtra("name", nameFromDb);
                intent.putExtra("username", userNameFromDb);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });
    }
}
