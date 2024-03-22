package com.example.my_pet_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.my_pet_shop.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class HomeActivity extends DrawerBase {


    ImageButton image_button_1,image_button_2,image_button_3;
  ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_home);




        ImageSlider imageSlider=findViewById(R.id.imageSlider);
        ArrayList<SlideModel>slideModels=new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.image_home, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_home_cat, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_home_dog, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


        image_button_1=findViewById(R.id.image_btn1);
        image_button_2=findViewById(R.id.image_btn2);
        image_button_3=findViewById(R.id.image_btn3);

        image_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Dog.class);
                startActivity(intent);

            }
        });


        image_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Cat.class);
                startActivity(intent);

            }
        });



        image_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Bird.class);
                startActivity(intent);

            }
        });



    }



}