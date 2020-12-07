package com.cookandroid.the_son;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;


public class SettingActivity extends AppCompatActivity {
    private Switch alramonoff, soundonoff, C1, C2, S1, S2;
    private ImageButton homeBtn, cameraBtn, healthBtn, finderBtn, settingBtn;
    ImageButton.OnClickListener imgbuttonListener;

    private AlramService mService;
    private boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        alramonoff = (Switch)findViewById(R.id.alramOnOff);
        soundonoff = (Switch)findViewById(R.id.soundOnOff);
        C1 = (Switch)findViewById(R.id.switchC1);
        C2 = (Switch)findViewById(R.id.switchC2);
        S1 = (Switch)findViewById(R.id.switchS1);
        S2 = (Switch)findViewById(R.id.switchS2);
        homeBtn = findViewById(R.id.homebtn);
        cameraBtn = findViewById(R.id.camerabtn);
        healthBtn = findViewById(R.id.healthbtn);
        finderBtn = findViewById(R.id.finderbtn);
        settingBtn = findViewById(R.id.settingbtn);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        switchState(pref);

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

    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        switchState(sharedPreferences);
    }

    //스위치 상태 불러오기
    public void switchState(SharedPreferences pref){

        alram.setAlramOnOff(pref.getInt("alramonoff",0));
        alram.setSoundOnOff(pref.getInt("soundonoff",0));
        alram.setBLDcorrect(pref.getInt("BLDC",0));
        alram.setWorkcorrect(pref.getInt("workC",0));
        alram.setBLDstretching(pref.getInt("BLDS",0));
        alram.setWorkstretching(pref.getInt("workS",0));
        alram.setStartHourC(pref.getInt("SHourC",9));
        alram.setStartMinC(pref.getInt("SMinC",0));
        alram.setEndHourC(pref.getInt("EHourC",18));
        alram.setEndMinC(pref.getInt("EMinC",0));
        alram.setStartHourS(pref.getInt("SHourS",9));
        alram.setStartMinS(pref.getInt("SMinS",0));
        alram.setEndHourS(pref.getInt("EHourS",18));
        alram.setEndMinS(pref.getInt("EMinS",0));

        if(alram.getAlramOnOff()==1)
            alramonoff.setChecked(true);
        else
            alramonoff.setChecked(false);
        if(alram.getSoundOnOff()==1)
            alramonoff.setChecked(true);
        else
            alramonoff.setChecked(false);
        if(alram.getBLDcorrect()==1)
            alramonoff.setChecked(true);
        else
            alramonoff.setChecked(false);
        if(alram.getBLDstretching()==1)
            alramonoff.setChecked(true);
        else
            alramonoff.setChecked(false);
        if(alram.getWorkcorrect()==1)
            alramonoff.setChecked(true);
        else
            alramonoff.setChecked(false);
        if(alram.getWorkstretching()==1)
            alramonoff.setChecked(true);
        else
            alramonoff.setChecked(false);
    }

    //알람 설정 스위치 전역변수로 알람 키기랑 알람 끄기
    // 가장 기초적인 조건
    public void AlramFunc(View view) {
        if(alramonoff.isChecked()) {
            alram.setAlramOnOff(1);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("alramononff",1);
            edit.apply();

            Intent intent = new Intent(SettingActivity.this, AlramService.class);
            intent.setAction("startForeground");
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        }
        else {
            alram.setAlramOnOff(0);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("alramononff",0);
            edit.apply();

            Intent intent = new Intent(SettingActivity.this, AlramService.class);
            intent.setAction("stopForeground");
            if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                stopService(intent);
            }
        }
    }
    //알람이 올때 소리를 낼지 안낼지
    public void SoundFunc(View view) {
        if(soundonoff.isChecked()) {
            alram.setSoundOnOff(1);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("soundonoff",1);
            edit.apply();
        }
        else {
            alram.setSoundOnOff(0);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("soundononff",0);
            edit.apply();
        }
    }
    //자세교정 아침,점심,저녁
    public void CBLDFunc(View view) {
        if(C1.isChecked()) {
            alram.setBLDcorrect(1);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("BLDC", 1);
            edit.apply();
        }
        else {
            alram.setBLDcorrect(0);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("BLDC",0);
            edit.apply();
        }
    }
    //자세교정 설정시간대
    public void CworkFunc(View view) {
        if(C2.isChecked()) {
            alram.setWorkcorrect(1);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("workC",1);
            edit.apply();
        }
        else {
            alram.setWorkcorrect(0);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("workC",0);
            edit.apply();
        }
    }
    //자세교정 시간대 설정
    public void CsetTimeFunc(View view) {
        Intent intent1 = new Intent(SettingActivity.this, Setting_alram1.class );
        startActivity(intent1);
    }
    //스트레칭 아침,점심,저녁
    public void SBLDFunc(View view) {
        if(S1.isChecked()) {
            alram.setBLDstretching(1);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("BLDS",1);
            edit.apply();
        }
        else {
            alram.setBLDstretching(0);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("BLDS",0);
            edit.apply();
        }
    }
    //스트레칭 설정시간대
    public void SworkFunc(View view) {
        if(S2.isChecked()) {
            alram.setWorkstretching(1);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("workS",1);
            edit.apply();
        }
        else {
            alram.setWorkstretching(0);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("workS",0);
            edit.apply();
        }
    }
    //스트레칭 시간대 설정
    public void SsetTimeFunc(View view) {
        Intent intent2 = new Intent(SettingActivity.this, Setting_alram2.class );
        startActivity(intent2);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // MyBinder와 연결될 것이며 IBinder 타입으로 넘어오는 것을 캐스팅하여 사용
            AlramService.MyBinder binder = (AlramService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 예기치 않은 종료
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        // 서비스와 연결
        Intent intent = new Intent(this, AlramService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 서비스와 연결 해제
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
}