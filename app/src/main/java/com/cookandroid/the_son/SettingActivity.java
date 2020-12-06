package com.cookandroid.the_son;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import static com.cookandroid.the_son.Activity_Loading.alram;

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
    //알람 설정 스위치 전역변수로 알람 키기랑 알람 끄기
    // 가장 기초적인 조건
    public void AlramFunc(View view) {
        if(alramonoff.isChecked()) {
            alram.setAlramOnOff(1);
            Log.d("My Service", "서비스 동작중!");
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
            /*
            Intent intent = new Intent(SettingActivity.this, AlramService.class);
            intent.setAction("startForeground");
            if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O) {
                //stopForegroundService(intent);
            } else {
                stopService(intent);
            }
            */
        }
    }
    //알람이 올때 소리를 낼지 안낼지
    public void SoundFunc(View view) {
        if(soundonoff.isChecked()) {
            alram.setSoundOnOff(1);
        }
        else {
            alram.setSoundOnOff(0);
        }
    }
    //자세교정 아침,점심,저녁
    public void CBLDFunc(View view) {
        if(C1.isChecked())
            alram.setBLDcorrect(1);
        else
            alram.setBLDcorrect(0);
    }
    //자세교정 설정시간대
    public void CworkFunc(View view) {
        if(C2.isChecked())
            alram.setWorkcorrect(1);
        else
            alram.setWorkcorrect(0);
    }
    //자세교정 시간대 설정
    public void CsetTimeFunc(View view) {
        Intent intent1 = new Intent(SettingActivity.this, Setting_alram1.class );
        startActivity(intent1);
    }
    //스트레칭 아침,점심,저녁
    public void SBLDFunc(View view) {
        if(S1.isChecked())
            alram.setBLDstretching(1);
        else
            alram.setBLDstretching(0);
    }
    //스트레칭 설정시간대
    public void SworkFunc(View view) {
        if(S2.isChecked())
            alram.setWorkstretching(1);
        else
            alram.setWorkstretching(0);
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