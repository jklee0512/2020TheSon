package com.cookandroid.the_son;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GridActivity extends AppCompatActivity {

    private ImageButton ibthand;
    private Button OKbtn, ibtmouse;
    private EditText text1, text2, text3, text4;
    private ImageButton homeBtn, cameraBtn, healthBtn, finderBtn, settingBtn;
    private File mFile;
    private String imgURL="http://121.190.18.135/";
    private Bitmap bmImg;
    back task;

    Button.OnClickListener buttonListener;
    ImageButton.OnClickListener imgbuttonListener;
    AlertDialog dialog;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        ibthand = findViewById(R.id.handhelp);
        ibtmouse = findViewById(R.id.mousefind);
        OKbtn = findViewById(R.id.okbtn);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText3);
        text3 = findViewById(R.id.editText4);
        text4 = findViewById(R.id.editText5);

        homeBtn = findViewById(R.id.homebtn);
        cameraBtn = findViewById(R.id.camerabtn);
        healthBtn = findViewById(R.id.healthbtn);
        finderBtn = findViewById(R.id.finderbtn);
        settingBtn = findViewById(R.id.settingbtn);

        fileread();

        buttonListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.okbtn:

                        // 손모형이 설정됬는지 확인
                        String handW = text1.getText().toString();
                        String handH= text2.getText().toString();
                        String mouseW= text3.getText().toString();
                        String mouseH= text4.getText().toString();
                        int grid_num= 1;   // 그리드 크기를 위한 번호

                        if(handW.equals("") || handH.equals("")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(GridActivity.this);
                            dialog = builder.setMessage("손크기가 빈칸 일수 없습니다.")
                                    .setPositiveButton("확인", null)
                                    .create();
                            dialog.show();
                        }
                        else if(mouseH.equals("") || mouseW.equals("")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(GridActivity.this);
                            dialog = builder.setMessage("마우스 크기가 빈칸 일수 없습니다.")
                                    .setPositiveButton("확인", null)
                                    .create();
                            dialog.show();
                        }
                        else {  //모든 수치가 입력되었을때
                            check = 1;
                            float handWidth = Float.valueOf(handW);
                            float handHeight = Float.valueOf(handH);
                            float mouseWidth = Float.valueOf(mouseW);
                            float mouseHeight = Float.valueOf(mouseH);

                            grid_num = checkGridSize(handWidth, handHeight, mouseWidth, mouseHeight);
                            String str = String.valueOf(grid_num);

                            task = new back();
                            task.execute(imgURL + "grid" + str + ".jpg");
                            filestore();
                        }
                        break;
                }
            }
        };

        imgbuttonListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.handhelp:
                        Intent intent2 = new Intent(GridActivity.this,PopHand.class);
                        startActivity(intent2);
                        break;
                    case R.id.mousefind:
                            Intent intent3 = new Intent(GridActivity.this, PopMouse.class);
                            startActivityForResult(intent3, 1);
                            break;
                    case R.id.camerabtn:
                        if(check == 0 ){
                            Toast.makeText(GridActivity.this, "설정된 손 모형이 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent5 = new Intent(GridActivity.this, Camera1Activity.class);
                            startActivity(intent5);
                            break;
                        }
                    case R.id.healthbtn:
                        Intent intent6 = new Intent(GridActivity.this,HealthActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.finderbtn:
                        Intent intent7 = new Intent(GridActivity.this,MouseActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.settingbtn:
                        Intent intent8 = new Intent(GridActivity.this,SettingActivity.class);
                        startActivity(intent8);
                        break;
                }
            }
        };

        OKbtn.setOnClickListener(buttonListener);
        ibthand.setOnClickListener(imgbuttonListener);
        ibtmouse.setOnClickListener(imgbuttonListener);
        homeBtn.setOnClickListener(imgbuttonListener);
        cameraBtn.setOnClickListener(imgbuttonListener);
        healthBtn.setOnClickListener(imgbuttonListener);
        finderBtn.setOnClickListener(imgbuttonListener);
        settingBtn.setOnClickListener(imgbuttonListener);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            text3.setText(data.getStringExtra("mouse_width"));
            text4.setText(data.getStringExtra("mouse_length"));
        }
    }
    public int checkGridSize(float hw, float hh, float mw, float mh){
        int handsize, mousesize;
        if(hw < 8.5){
            if(hh < 17) handsize = 1;
            else if (hh < 20) handsize = 2;
            else handsize = 3;
        }
        else if(hw < 10){
            if(hh < 17) handsize = 4;
            else if (hh < 20) handsize = 5;
            else handsize = 6;
        }
        else {
            if(hh < 17) handsize = 7;
            else if (hh < 20) handsize = 8;
            else handsize = 9;
        }
        if(mw < 8.5){
            if(mh < 17) mousesize = 1;
            else if (mh < 20) mousesize= 2;
            else mousesize= 3;
        }
        else if(mw < 10){
            if(mh < 17) mousesize = 4;
            else if (mh < 20) mousesize = 5;
            else mousesize= 6;
        }
        else {
            if(mh < 17) mousesize = 7;
            else if (mh < 20) mousesize = 8;
            else mousesize = 9;
        }
        // 사이즈 반환을 어떻게 할지
        return handsize;
    }

    private class back extends AsyncTask<String, Integer,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... url){
            try {
                URL myfileurl = new URL(url[0]);
                HttpURLConnection conn = (HttpURLConnection)myfileurl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(GridActivity.this, "no hand", Toast.LENGTH_SHORT).show();
            }
            return bmImg;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imagestore();
        }
    }

    public String getImageFileName(){ return "hand.jpg"; }

    public void imagestore(){
        mFile = new File(getExternalFilesDir(null), getImageFileName());
        OutputStream out =null;
        try {
            out = new FileOutputStream(mFile);
            if(out != null){
                bmImg.compress(Bitmap.CompressFormat.JPEG, 75, out);
                //Toast.makeText(GridActivity.this, "image save",Toast.LENGTH_SHORT).show();
            }
            out.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(GridActivity.this, "FileNotFounder", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            Toast.makeText(GridActivity.this, "InOuter", Toast.LENGTH_SHORT).show();
        }
    }
    public void filestore(){
        String s1 = "hand_height = " + text1.getText().toString() + "\n";
        String s2 = "hand_width = " + text2.getText().toString() + "\n";
        String s3 = "mouse_height = " + text3.getText().toString() + "\n";
        String s4 = "mouse_width = " + text4.getText().toString() + "\n";
        String playerdata = s1.concat(s2.concat(s3.concat(s4)));

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(getExternalFilesDir(null)+"data.txt",false));
            bw.write(playerdata);
            bw.close();
            Toast.makeText(this,"저장완료",Toast.LENGTH_SHORT).show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void fileread() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getExternalFilesDir(null)+ "data.txt"));
            String line = "";
            int index;
            String num = "";
            for(int i=1; (line=br.readLine())!=null; i++) {
                try {
                    index = line.indexOf("=");
                    num = line.substring(index+2);
                }
                catch(NullPointerException e) {
                    e.printStackTrace();
                }
                if(i==1) text1.setText(num);
                else if(i==2) text2.setText(num);
                else if(i==3) text3.setText(num);
                else if(i==4) text4.setText(num);
            }
            check = 1;
        }
        catch(IOException e){
            e.printStackTrace();
            check=0;
        }
    }
}
