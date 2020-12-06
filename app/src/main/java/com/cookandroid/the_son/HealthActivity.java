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

public class HealthActivity extends AppCompatActivity {

    private ImageButton homeBtn, cameraBtn, healthBtn, finderBtn, settingBtn;
    ImageButton.OnClickListener imgbuttonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        Button btnfinger = findViewById(R.id.button1);
        Button btnmassage = findViewById(R.id.button2);
        Button btnpalm = findViewById(R.id.button3);

        Fragment inifragment= new Fragment_diagnosis();
        FragmentManager inifragmentManager = getSupportFragmentManager();
        FragmentTransaction initransaction = inifragmentManager.beginTransaction();
        initransaction.replace(R.id.container_scroll, inifragment);
        initransaction.commit();

        btnfinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment= new Fragment_finger();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container_scroll, fragment);
                transaction.commit();
            }
        });
        btnmassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment= new Fragment_massage();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container_scroll, fragment);
                transaction.commit();
            }
        });
        btnpalm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment= new Fragment_palm();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container_scroll, fragment);
                transaction.commit();
            }
        });

        homeBtn = findViewById(R.id.homebtn);
        cameraBtn = findViewById(R.id.camerabtn);
        healthBtn = findViewById(R.id.healthbtn);
        finderBtn = findViewById(R.id.finderbtn);
        settingBtn = findViewById(R.id.settingbtn);

        imgbuttonListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.homebtn:
                        Intent intent4 = new Intent(HealthActivity.this,GridActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.camerabtn:
                        Intent intent5 = new Intent(HealthActivity.this,Camera1Activity.class);
                        startActivity(intent5);
                        break;
                    case R.id.finderbtn:
                        Intent intent7 = new Intent(HealthActivity.this,MouseActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.settingbtn:
                        Intent intent8 = new Intent(HealthActivity.this,SettingActivity.class);
                        startActivity(intent8);
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