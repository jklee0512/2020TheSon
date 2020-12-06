package com.cookandroid.the_son;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Random;

public class AnalyzeLoading extends AppCompatActivity {

    private static final int PORT = 8080; // port
    String path = "/storage/emulated/0/Android/data/com.cookandroid.the_son/files/";
    String img1 = "/storage/emulated/0/Android/data/com.cookandroid.the_son/files/pic1.jpg";
    String img2 = "/storage/emulated/0/Android/data/com.cookandroid.the_son/files/pic2.jpg";
    String dataresult;
    String ip = "192.168.43.244"; // ip
    // 소켓통신
    Socket sock;
    DataOutputStream dos;
    BufferedInputStream bis;
    FileOutputStream fos, fos2;
    InputStream dis;
    Bitmap b, c;
    Boolean isTrue=false;

    int[] result = new int[2]; // 사진1,2에 대한 점수
    int num = 0;

    ////////////////////소켓 통신////////////////////
    //권한체크//
    public void checkPermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    //소켓연결//
    private void connect(String ip, int port) {
        b = BitmapFactory.decodeFile(img1);
        c = BitmapFactory.decodeFile(img2);
        try {
            Log.d("TCP", "server connecting");
            sock = new Socket(ip, port);
            System.out.println("데이터찾는중");
            dos = new DataOutputStream(sock.getOutputStream());
            bis = new BufferedInputStream(new ByteArrayInputStream(bitmapToByteArray(b)));
            checkUpdate.start();
            sendThread.start();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //사진 전송//
    public Thread sendThread = new Thread() {
        @Override
        public void run() {
            try {
                int len = bitmapToByteArray(b).length;
                byte[] bsize = new byte[]{
                        (byte) ((len >> 24)),
                        (byte) ((len >> 16)),
                        (byte) ((len >> 8)),
                        (byte) ((len)),
                };
                dos.write(bsize);
                int size = 65535;
                byte[] data = new byte[size];
                try {
                    while ((len = bis.read(data)) != -1) {
                        dos.write(data, 0, len);
                    }

                    System.out.println("데이터보내기 끝 직전");
                    dos.flush();
                    //bis.close();
                    System.out.println("데이터끝");
                    System.out.println("보낸 파일의 사이즈 : " + bitmapToByteArray(b).length);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                int len2 = bitmapToByteArray(c).length;
                bis = new BufferedInputStream(new ByteArrayInputStream(bitmapToByteArray(c)));
                byte[] bsize = new byte[]{
                        (byte) ((len2 >> 24)),
                        (byte) ((len2 >> 16)),
                        (byte) ((len2 >> 8)),
                        (byte) ((len2)),
                };
                dos.write(bsize);
                int size2 = 65535;
                byte[] data2 = new byte[size2];
                try {
                    while ((len2 = bis.read(data2)) != -1) {
                        dos.write(data2, 0, len2);
                    }

                    System.out.println("데이터보내기 끝 직전");
                    dos.flush();
                    //bis.close();
                    System.out.println("데이터끝");
                    System.out.println("보낸 파일의 사이즈 : " + bitmapToByteArray(c).length);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    //알고리즘처리결과 받아오기
    public Thread checkUpdate = new Thread() {
        @Override
        public void run() {
            //결과값 받기//
            try {
                dis = sock.getInputStream();
                while (true) {
                    result[num] = dis.read();
                    num++;
                    if (num == 2)
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < num; i++) {
                Log.w("r", String.valueOf(result[i]));
            }
            //컨투어 이미지 받아서 파일쓰기//
            try {
                File file = new File(path + "Contour.jpg");
                file.createNewFile();
                File file2 = new File(path + "Contour2.jpg");
                file2.createNewFile();
                fos = new FileOutputStream(file, false);
                fos2 = new FileOutputStream(file2, false);
                int img;
                int len;
                byte[] lenImg=new byte[3];
                try {
                    dis.read(lenImg);
                    len=    ((lenImg[0] & 0xFF) << 16) |
                            ((lenImg[1] & 0xFF) << 8) |
                            ((lenImg[2] & 0xFF));
                    Log.w("len", String.valueOf(len));
                    byte[] imgData = new byte[len];
                    Thread.sleep(2000);
                    img = dis.read(imgData);
                    fos.write(imgData, 0, img);
                    Thread.sleep(2000);
                    dis.read(lenImg);
                    len=    ((lenImg[0] & 0xFF) << 16) |
                            ((lenImg[1] & 0xFF) << 8) |
                            ((lenImg[2] & 0xFF));
                    imgData = new byte[len];
                    Thread.sleep(2000);
                    img = dis.read(imgData);
                    fos2.write(imgData, 0, img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isTrue=true;
                fos.close();
                fos2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    //이미지 bytearray화//
    public static byte[] bitmapToByteArray(Bitmap $bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        $bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
//////////////////////////////////////////////////////////
    private TextView text;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        String t1="L";
        String t2="Lo";
        String t3="Loa";
        String t4="Load";
        String t5="Loadi";
        String t6="Loadin";
        String t7="Loading";
        String t8="Loading.";
        String t9="Loading..";
        String t10="Loading...";
        int count=0;
        @Override
        public void run() {
            Random rnd= new Random();
            text.setTextColor(Color.rgb(rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255)));
            switch (count){
                case 0:text.setText(t1); count++; break;
                case 1:text.setText(t2); count++; break;
                case 2:text.setText(t3); count++; break;
                case 3:text.setText(t4); count++; break;
                case 4:text.setText(t5); count++; break;
                case 5:text.setText(t6); count++; break;
                case 6:text.setText(t7); count++; break;
                case 7:text.setText(t8); count++; break;
                case 8:text.setText(t9); count++; break;
                case 9:text.setText(t10); count=0; break;
            }
            if(isTrue){
                handler.removeCallbacks(runnable);
                float score = (result[0] + result[1]) / 2;
                Log.w("Score", String.valueOf(score));
                //dataresult = Float.valueOf(score).toString();

                Intent intent = new Intent(AnalyzeLoading.this, AnalyzeActivity.class);
                intent.putExtra("frontscore",Float.valueOf(result[0]).toString());
                intent.putExtra("sidescore",Float.valueOf(result[1]).toString());
                startActivity(intent);
            }
            else {
                handler.postDelayed(runnable, 200);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyze_loading);

        text= (TextView)findViewById(R.id.loading);
        (new Thread() {
            public void run() {
                try {
                    connect(ip, PORT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        handler.post(runnable);
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

}
