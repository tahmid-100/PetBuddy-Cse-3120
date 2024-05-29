package com.example.petbuddy;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.regex.Pattern;

public class LoginHelper {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Pattern emailPattern;

    public LoginHelper(FirebaseAuth auth, DatabaseReference database, Pattern emailPattern) {
        mAuth = auth;
        mDatabase = database;
        this.emailPattern = emailPattern;
    }

    public boolean isEmailValid(String email) {
        return email != null && emailPattern.matcher(email).matches();
    }

    public boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }

    public void signIn(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

    public Query getUserQuery(String phone) {
        return mDatabase.orderByChild("phone").equalTo(phone);
    }
}
