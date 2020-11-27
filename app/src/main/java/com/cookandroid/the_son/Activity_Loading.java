package com.cookandroid.the_son;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Activity_Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),GridActivity.class);
                startActivity(intent);
                finish();
            }
        }, 200);  //시간설정
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}

