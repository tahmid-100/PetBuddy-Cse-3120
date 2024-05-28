package com.example.petbuddy;

import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityHelper {

    private ImageView img;
    private TextView slogan;

    public MainActivityHelper(ImageView img, TextView slogan) {
        this.img = img;
        this.slogan = slogan;
    }

    public void setImageResource(int resId) {
        img.setImageResource(resId);
    }

    public int getImageResource() {
        return (Integer) img.getTag();
    }

    public void setSloganText(String text) {
        slogan.setText(text);
    }

    public String getSloganText() {
        return slogan.getText().toString();
    }
}