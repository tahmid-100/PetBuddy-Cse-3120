package com.example.my_pet_shop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;


import com.example.my_pet_shop.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends DrawerBase {

    DatabaseReference databaseReference;
    FirebaseDatabase rootnode,node;
    DatabaseReference reference,userReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    Button goHomeBtn, updateBtn,deleteBtn;

    TextView fullNameTextView, usernameTextView, emailTextView, phoneTextView, passwordTextView;
    String idPhone, email;
    ActivityProfileBinding activityProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Petbuddy").child("users");
        goHomeBtn = findViewById(R.id.goHomeBtn);
        updateBtn = findViewById(R.id.update_btn);
        fullNameTextView = findViewById(R.id.fullnameEditText);
        usernameTextView = findViewById(R.id.usernameEditText);
        emailTextView = findViewById(R.id.emailEditText);
        phoneTextView = findViewById(R.id.phonenumEditText);
        passwordTextView = findViewById(R.id.passwordEditText);
        deleteBtn=findViewById(R.id.deleteBtn);
        idPhone = phoneTextView.getText().toString();

        showUserData();

        goHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoned=phoneTextView.getText().toString();
                deleteProfile(phoned);
            }
        });

    }

    private void showUpdateDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText updateName = dialogView.findViewById(R.id.updateName);
        final EditText updateUsername = dialogView.findViewById(R.id.updateUsername);
        final EditText updateEmail = dialogView.findViewById(R.id.updateEmail);
        final EditText updatePhone = dialogView.findViewById(R.id.updatePhone);
        final EditText updatePassword = dialogView.findViewById(R.id.updatePassword);

        // Set current values to the EditTexts
        updateName.setText(fullNameTextView.getText().toString());
        updateUsername.setText(usernameTextView.getText().toString());
        updateEmail.setText(emailTextView.getText().toString());
        updatePhone.setText(phoneTextView.getText().toString());
        updatePassword.setText(passwordTextView.getText().toString());

        dialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Update the text views with the new values
                fullNameTextView.setText(updateName.getText().toString());
                usernameTextView.setText(updateUsername.getText().toString());
                emailTextView.setText(updateEmail.getText().toString());
                phoneTextView.setText(updatePhone.getText().toString());
                passwordTextView.setText(updatePassword.getText().toString());
                // You can update the Firebase database here if needed

                updateUserData(updateName.getText().toString(),
                        updateUsername.getText().toString(),
                        updateEmail.getText().toString(),
                        updatePhone.getText().toString(),
                        updatePassword.getText().toString());


            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Cancelled, do nothing
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public void showUserData() {
        // Existing code to show user data in TextViews

        Intent intent=getIntent();

        String nameUser= intent.getStringExtra("name");
        String passUser= intent.getStringExtra("password");
        String phoneUser= intent.getStringExtra("phone");
        String emailUser= intent.getStringExtra("email");
        String userNameUser= intent.getStringExtra("username");

        fullNameTextView.setText(nameUser);
        usernameTextView.setText(userNameUser);
        phoneTextView.setText(phoneUser);
        passwordTextView.setText(passUser);
        emailTextView.setText(emailUser);
    }


    private void updateUserData(String name, String username, String email, String phone, String password) {
        rootnode=FirebaseDatabase.getInstance();
        reference= rootnode.getReference("Petbuddy/users");

        reference.child(phone).child("name").setValue(name);
        reference.child(phone).child("username").setValue(username);
        reference.child(phone).child("email").setValue(email);
        //reference.child(phone).child("phone").setValue(phone);
        reference.child(phone).child("password").setValue(password);
    }


   public  void deleteProfile(String phoned){

       node = FirebaseDatabase.getInstance();
       userReference = node.getReference("Petbuddy/users").child(phoned);

       // Remove the user data from the database
       userReference.removeValue().addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               // Deletion successful
               // You might want to navigate to another screen or perform other actions
               // after the profile is deleted
               Intent intent = new Intent(ProfileActivity.this, Login.class);
               startActivity(intent);
               finish(); // Close the current activity
           } else {
               // Handle the deletion failure
               // You might want to show an error message or perform other actions
               // to inform the user about the issue
               Toast.makeText(ProfileActivity.this, "Failed to delete profile", Toast.LENGTH_SHORT).show();
           }
       });


   }


}
