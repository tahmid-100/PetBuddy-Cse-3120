/**
 * Bird class represents an activity to display bird breeds and their details.
 *
 * This activity fetches data from Firebase Realtime Database to display
 * information about different bird breeds including their names, prices, ages,
 * and images. Users can view details of each bird breed and proceed to payment.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Bird extends AppCompatActivity {

    /**
     * The container layout to hold bird breeds.
     */
    public LinearLayout breedContainer;

    /**
     * Reference to the Firebase Realtime Database.
     */
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        // Initialize breedContainer layout
        breedContainer = findViewById(R.id.breedContainer);

        // Create and configure title TextView
        TextView birdsText = new TextView(this);
        birdsText.setText("Birds");
        birdsText.setTextSize(24);
        birdsText.setTypeface(null, Typeface.BOLD);
        birdsText.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 16, 0, 16); // Set margins top and bottom
        birdsText.setLayoutParams(params);

        // Add the title TextView to the breedContainer LinearLayout
        breedContainer.addView(birdsText);

        // Firebase Realtime Database reference for bird breeds
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Petbuddy/Pet/bird");

        // Read the data from Firebase Database
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if the snapshot exists and has a value
                if (snapshot.exists()) {
                    for (DataSnapshot breedSnapshot : snapshot.getChildren()) {
                        // Extract breed details from the snapshot
                        String breedName = breedSnapshot.getKey();
                        String imageUrl = breedSnapshot.child("imageUrl").getValue(String.class);
                        String name = breedSnapshot.child("name").getValue(String.class);
                        int price = breedSnapshot.child("price").getValue(Integer.class);
                        int age = breedSnapshot.child("age").getValue(Integer.class);

                        // Create a layout for each breed
                        LinearLayout breedLayout = new LinearLayout(Bird.this);
                        breedLayout.setOrientation(LinearLayout.VERTICAL);

                        // Create and configure TextViews for breed details
                        TextView breedNameTextView = new TextView(Bird.this);
                        breedNameTextView.setText("Breed: " + breedName);
                        breedNameTextView.setTextSize(20);
                        breedLayout.addView(breedNameTextView);

                        TextView nameTextView = new TextView(Bird.this);
                        nameTextView.setText("Name: " + name);
                        nameTextView.setTextSize(18);
                        breedLayout.addView(nameTextView);

                        TextView priceTextView = new TextView(Bird.this);
                        priceTextView.setText("Price: $" + price);
                        priceTextView.setTextSize(18);
                        breedLayout.addView(priceTextView);

                        TextView ageTextView = new TextView(Bird.this);
                        ageTextView.setText("Age: " + age + " years");
                        ageTextView.setTextSize(18);
                        breedLayout.addView(ageTextView);

                        // Create and configure ImageView for breed image
                        ImageView petImageView = new ImageView(Bird.this);
                        Picasso.get().load(imageUrl).into(petImageView);
                        breedLayout.addView(petImageView);

                        // Create and configure Pay Button
                        Button payButton = new Button(Bird.this);
                        payButton.setText("Pay");
                        payButton.setId(View.generateViewId());
                        payButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Pass breed details to Payment class using Intent
                                Intent intent = new Intent(Bird.this, Payment.class);
                                intent.putExtra("BREED_PRICE", price);
                                intent.putExtra("PET_NAME", name);
                                intent.putExtra("AGE", age);
                                intent.putExtra("IMAGE_URL", imageUrl);
                                intent.putExtra("BREED_NAME", breedName);
                                startActivity(intent);
                            }
                        });
                        breedLayout.addView(payButton);

                        // Add margins between elements
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams.setMargins(0, 0, 0, 20); // Set bottom margin
                        breedLayout.setLayoutParams(layoutParams);

                        // Add the breed layout to the main container
                        breedContainer.addView(breedLayout);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
