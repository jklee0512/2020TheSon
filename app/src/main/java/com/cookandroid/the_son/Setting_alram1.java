package com.cookandroid.the_son;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class Setting_alram1 extends AppCompatActivity {
    private Button t1, t2;
    private String startT, endT;
    private int mHour, mMinute;

    private TimePickerDialog.OnTimeSetListener StimeSetListenr = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(hourOfDay > 12) {
                hourOfDay = hourOfDay - 12;
                startT = String.format("PM %02d:%02d", hourOfDay, minute);
            }
            else
                startT = String.format("AM %02d:%02d",hourOfDay, minute);
            t1.setText(startT);
            Activity_Loading.alram.setStartHourC(hourOfDay);
            Activity_Loading.alram.setStartMinC(minute);
        }
    };
    private TimePickerDialog.OnTimeSetListener EtimeSetListenr = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(hourOfDay > 12) {
                hourOfDay = hourOfDay - 12;
                endT = String.format("PM %02d:%02d", hourOfDay, minute);
            }
            else
                endT = String.format("AM %02d:%02d",hourOfDay, minute);
            t2.setText(endT);
            Activity_Loading.alram.setEndHourC(hourOfDay);
            Activity_Loading.alram.setEndMinC(minute);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_alram1);
        t1 = findViewById(R.id.timeS);
        t2 = findViewById(R.id.timeE);

    }

    public void setStartTime(View view) {
        //TimePickerFragment timePickerFragment = new TimePickerFragment();
        //timePickerFragment.show(getSupportFragmentManager(), "timePicker");
        new TimePickerDialog(Setting_alram1.this, StimeSetListenr, mHour, mMinute, false).show();
        }

    public void setEndTime(View view) {
        //TimePickerFragment timePickerFragment = new TimePickerFragment();
        //timePickerFragment.show(getSupportFragmentManager(), "timePicker");
        new TimePickerDialog(Setting_alram1.this, EtimeSetListenr, mHour, mMinute, false).show();
    }

    public void BackFunc(View view) {
        Intent intent = new Intent(Setting_alram1.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }
}
