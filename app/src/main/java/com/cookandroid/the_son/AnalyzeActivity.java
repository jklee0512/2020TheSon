package com.cookandroid.the_son;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

public class AnalyzeActivity extends AppCompatActivity {

    private static final int SENSOR_ORIENTATION_DEFAULT_DEGREES = 90;
    ImageButton.OnClickListener imgbuttonListener;
    String img1 = "/storage/emulated/0/Android/data/com.cookandroid.the_son/files/pic1.jpg";
    String img2 = "/storage/emulated/0/Android/data/com.cookandroid.the_son/files/pic2.jpg";
    String img3 = "/storage/emulated/0/Android/data/com.cookandroid.the_son/files/Contour.jpg";
    String img4 = "/storage/emulated/0/Android/data/com.cookandroid.the_son/files/Contour2.jpg";

    TextView text;
    ImageView mImageview1, mImageview2, mImageview3, mImageview4;
    ImageButton homeBtn, cameraBtn, healthBtn, finderBtn, settingBtn;

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);
        homeBtn = findViewById(R.id.homebtn);
        cameraBtn = findViewById(R.id.camerabtn);
        healthBtn = findViewById(R.id.healthbtn);
        finderBtn = findViewById(R.id.finderbtn);
        settingBtn = findViewById(R.id.settingbtn);

        Intent intent=getIntent();
        String fscore=intent.getExtras().getString("frontscore");
        fscore += "°";
        TextView getfScore=findViewById(R.id.fscore);
        getfScore.setText(fscore);

        String sscore=intent.getExtras().getString("sidescore");
        sscore += "°";
        TextView getsScore=findViewById(R.id.sscore);
        getsScore.setText(sscore);

        imgbuttonListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.homebtn:
                        Intent intent4 = new Intent(AnalyzeActivity.this, GridActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.camerabtn:
                        Intent intent5 = new Intent(AnalyzeActivity.this, Camera1Activity.class);
                        startActivity(intent5);
                        break;
                    case R.id.healthbtn:
                        Intent intent6 = new Intent(AnalyzeActivity.this, HealthActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.finderbtn:
                        Intent intent7 = new Intent(AnalyzeActivity.this, MouseActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.settingbtn:
                        Intent intent8 = new Intent(AnalyzeActivity.this, SettingActivity.class);
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
        setView();
    }


    public void setView() {
        Bitmap bmp1 = BitmapFactory.decodeFile(img1);
        if (bmp1 != null) {
            bmp1 = rotateImage(bmp1, SENSOR_ORIENTATION_DEFAULT_DEGREES);
        }
        mImageview1 = findViewById(R.id.original_top);
        mImageview1.setImageBitmap(bmp1);

        Bitmap bmp2 = BitmapFactory.decodeFile(img2);
        if (bmp2 != null) {
            bmp2 = rotateImage(bmp2, SENSOR_ORIENTATION_DEFAULT_DEGREES);
        }
        mImageview2 = findViewById(R.id.original_left);
        mImageview2.setImageBitmap(bmp2);

        Bitmap bmp3 = BitmapFactory.decodeFile(img3);
        mImageview3 = findViewById(R.id.alg_top);
        mImageview3.setImageBitmap(bmp3);

        Bitmap bmp4 = BitmapFactory.decodeFile(img4);
        mImageview4 = findViewById(R.id.alg_left);
        mImageview4.setImageBitmap(bmp4);
    }

}
