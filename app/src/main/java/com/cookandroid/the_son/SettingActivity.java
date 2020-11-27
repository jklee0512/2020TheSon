package com.cookandroid.the_son;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private ImageButton homeBtn, cameraBtn, healthBtn, finderBtn, settingBtn;
    ImageButton.OnClickListener imgbuttonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button text = (Button) findViewById(R.id.alramkind);
        homeBtn = findViewById(R.id.homebtn);
        cameraBtn = findViewById(R.id.camerabtn);
        healthBtn = findViewById(R.id.healthbtn);
        finderBtn = findViewById(R.id.finderbtn);
        settingBtn = findViewById(R.id.settingbtn);

        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingActivity.this, "확인",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, Setting_alram.class);
                startActivity(intent);
            }
        });

        imgbuttonListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.homebtn:
                        Intent intent4 = new Intent(SettingActivity.this,GridActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.camerabtn:
                        Intent intent5 = new Intent(SettingActivity.this,Camera1Activity.class);
                        startActivity(intent5);
                        break;
                    case R.id.healthbtn:
                        Intent intent6 = new Intent(SettingActivity.this,HealthActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.finderbtn:
                        Intent intent7 = new Intent(SettingActivity.this,MouseActivity.class);
                        startActivity(intent7);
                        break;
                }
            }
        };

        homeBtn.setOnClickListener(imgbuttonListener);
        cameraBtn.setOnClickListener(imgbuttonListener);
        healthBtn.setOnClickListener(imgbuttonListener);
        finderBtn.setOnClickListener(imgbuttonListener);
        settingBtn.setOnClickListener(imgbuttonListener);
    }
}