package com.cookandroid.the_son;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class Setting_alram1 extends AppCompatActivity {
    private Button t1, t2;
    private String startT, endT;
    private String tempS, tempE;
    private int mHour, mMinute;

    private TimePickerDialog.OnTimeSetListener StimeSetListenr = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(hourOfDay > 12) {
                startT = String.format(Locale.getDefault(), "PM %02d:%02d", hourOfDay-12, minute);
            }
            else
                startT = String.format(Locale.getDefault(), "AM %02d:%02d",hourOfDay, minute);
            t1.setText(startT);
            Activity_Loading.alram.setStartHourC(hourOfDay);
            Activity_Loading.alram.setStartMinC(minute);
        }
    };
    private TimePickerDialog.OnTimeSetListener EtimeSetListenr = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(hourOfDay > 12) {
                endT = String.format(Locale.getDefault(),"PM %02d:%02d", hourOfDay-12, minute);
            }
            else
                endT = String.format(Locale.getDefault(),"AM %02d:%02d",hourOfDay, minute);
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
        if(Activity_Loading.alram.getStartHourC() > 12)
            tempS = String.format(Locale.getDefault(),"PM %02d:%02d", Activity_Loading.alram.getStartHourC() - 12, Activity_Loading.alram.getStartMinC());
        else
            tempS = String.format(Locale.getDefault(),"AM %02d:%02d", Activity_Loading.alram.getStartHourC(), Activity_Loading.alram.getStartMinC());
        if(Activity_Loading.alram.getEndHourC() > 12)
            tempE = String.format(Locale.getDefault(),"PM %02d:%02d", Activity_Loading.alram.getEndHourC() - 12, Activity_Loading.alram.getEndMinC());
        else
            tempE = String.format(Locale.getDefault(),"AM %02d:%02d", Activity_Loading.alram.getEndHourC(), Activity_Loading.alram.getEndMinC());

        t1.setText(tempS);
        t2.setText(tempE);
    }

    public void setStartTime(View view) {
        new TimePickerDialog(Setting_alram1.this, StimeSetListenr, mHour, mMinute, false).show();
        }

    public void setEndTime(View view) {
        new TimePickerDialog(Setting_alram1.this, EtimeSetListenr, mHour, mMinute, false).show();
    }

    public void BackFunc(View view) {
        Intent intent = new Intent(Setting_alram1.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }
}
