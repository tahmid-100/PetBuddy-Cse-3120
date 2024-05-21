public class Bird extends AppCompatActivity {


    private LinearLayout breedContainer;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        breedContainer = findViewById(R.id.breedContainer);

        // Assume "dog" is the pet type, you can change it based on your requirements
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Petbuddy/Pet/bird");

        // Read the data from Firebase Database
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if the snapshot exists and has a value
                if (snapshot.exists()) {
                    for (DataSnapshot breedSnapshot : snapshot.getChildren()) {
                        String breedName = breedSnapshot.getKey();
                        String imageUrl = breedSnapshot.child("imageUrl").getValue(String.class);
                        String name = breedSnapshot.child("name").getValue(String.class);
                        int price = breedSnapshot.child("price").getValue(Integer.class);
                        int age = breedSnapshot.child("age").getValue(Integer.class);

                        // Create a new layout for each breed
                        LinearLayout breedLayout = new LinearLayout(Bird.this);
                        breedLayout.setOrientation(LinearLayout.VERTICAL);

                        // Create and configure TextViews
                        TextView breedNameTextView = new TextView(Bird.this);
                        breedNameTextView.setText("Breed: " + breedName);
                        breedNameTextView.setTextSize(20); // Set text size
                        breedLayout.addView(breedNameTextView);

                        TextView nameTextView = new TextView(Bird.this);
                        nameTextView.setText("Name: " + name);
                        nameTextView.setTextSize(18); // Set text size
                        breedLayout.addView(nameTextView);

                        TextView priceTextView = new TextView(Bird.this);
                        priceTextView.setText("Price: $" + price);
                        priceTextView.setTextSize(18); // Set text size
                        breedLayout.addView(priceTextView);

                        TextView ageTextView = new TextView(Bird.this);
                        ageTextView.setText("Age: " + age + " years");
                        ageTextView.setTextSize(18); // Set text size
                        breedLayout.addView(ageTextView);

                        // Create and configure ImageView
                        ImageView petImageView = new ImageView(Bird.this);
                        Picasso.get().load(imageUrl).into(petImageView);
                        breedLayout.addView(petImageView);

                        Button payButton = new Button(Bird.this);
                        payButton.setText("Pay");
                        payButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Pass the price to the Payment class using Intent
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