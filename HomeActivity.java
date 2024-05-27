/**
 * HomeActivity represents the main activity of the pet shop application.
 * It displays a home screen with image sliders and buttons to navigate to different pet categories.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.my_pet_shop.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class HomeActivity extends DrawerBase {

    /** Image buttons for navigating to different pet categories. */
    private ImageButton image_button_1, image_button_2, image_button_3;

    /** Binding for the activity home layout. */
    ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize image slider
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image_home, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_home_cat, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_home_dog, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        // Initialize image buttons
        image_button_1 = findViewById(R.id.image_btn1);
        image_button_2 = findViewById(R.id.image_btn2);
        image_button_3 = findViewById(R.id.image_btn3);

        // Set click listeners for image buttons to navigate to respective pet categories
        image_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Dog.class));
            }
        });

        image_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Cat.class));
            }
        });

        image_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Bird.class));
            }
        });
    }
}
