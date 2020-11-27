package com.cookandroid.the_son;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Setting_alram extends AppCompatActivity {
    private TextView T1, T2;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_alram);

        back = findViewById(R.id.backbutton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting_alram.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
