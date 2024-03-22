package com.example.my_pet_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private  static int Splash_Screen=4500;
    Animation topanim,bottomanim;
    ImageView img;
    TextView logo,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topanim=AnimationUtils.loadAnimation(this,R.anim.top_anim_splash_sc);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_anim_splash_sc);
        img=findViewById(R.id.img);
        //logo=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView3);
        img.setAnimation(topanim);
        //logo.setAnimation(bottomanim);
        slogan.setAnimation(bottomanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();

            }
        },Splash_Screen);
    }
}