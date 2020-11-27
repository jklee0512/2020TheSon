package com.cookandroid.the_son;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.Resource;

public class PopHand extends AppCompatActivity {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_hand);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;
        getWindow().setLayout((int)(width*0.6),(int)(height*0.2));

        img = (ImageView)findViewById(R.id.handmeasure);

    }
}
