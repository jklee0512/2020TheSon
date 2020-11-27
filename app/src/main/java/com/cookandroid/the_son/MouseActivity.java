package com.cookandroid.the_son;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MouseActivity extends AppCompatActivity {

    public Button btnOK, btnHide;
    private ImageButton homeBtn, cameraBtn, healthBtn, finderBtn, settingBtn;
    ImageButton.OnClickListener imgbuttonListener;
    private CheckBox wire1Btn, wire2Btn, wire3Btn, weight1Btn, weight2Btn, weight3Btn;
    ListView listView;

    private TableLayout OptionList;
    int check = 0;
    boolean isTrue = false;

    private ArrayList<search_item> mylist = new ArrayList<>();  //원본 리스트
    private ArrayList<search_item> selectlist = new ArrayList<>();  //출력마다 바뀌는 출력용 리스트
    private ListViewAdapter adapter = new ListViewAdapter(MouseActivity.this);

    String myJSON;
    JSONArray jdata = null;
    private static final String Tag_name = "mouse_name";
    private static final String Tag_length= "mouse_length"; //세로
    private static final String Tag_width = "mouse_width";  //가로
    private static final String Tag_height = "mouse_height";   //높이
    private static final String Tag_weight = "mouse_weight";    //무게
    private static final String Tag_wire = "mouse_wire";        //유무선
    private static final String Tag_picture = "mouse_picture";  //사진
    private static final String Tag_site = "mouse_site";        //상세내용 페이지
    private String url = "http://121.190.18.135/mouse_suggest.php";

    private Handler handler1 = new Handler();
    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            if(mylist.size()==244) {
                handler1.removeCallbacks(runnable1);
                adapter = new ListViewAdapter(MouseActivity.this, selectlist);
                listView.setAdapter(adapter);
            }
            else {
                handler1.postDelayed(runnable1, 200);
            }
        }
    };
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(isTrue) {
                handler.removeCallbacks(runnable);
                for(int i=0;i<selectlist.size();i++)
                    adapter.addItem(selectlist.get(i));
                listView.setAdapter(adapter);
            }
            else {
                handler.postDelayed(runnable, 200);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        OptionList = findViewById(R.id.checker);
        wire1Btn = findViewById(R.id.wire1);
        wire2Btn = findViewById(R.id.wire2);
        wire3Btn = findViewById(R.id.wire3);
        weight1Btn = findViewById(R.id.weight1);
        weight2Btn = findViewById(R.id.weight2);
        weight3Btn = findViewById(R.id.weight3);
        btnOK = findViewById(R.id.okbtn);
        btnHide = findViewById(R.id.hide);
        homeBtn = findViewById(R.id.homebtn);
        cameraBtn = findViewById(R.id.camerabtn);
        healthBtn = findViewById(R.id.healthbtn);
        finderBtn = findViewById(R.id.finderbtn);
        settingBtn = findViewById(R.id.settingbtn);
        listView = findViewById(R.id.listV);

        //Json에 있는 데이터 불러오기
        (new Thread() {
            public void run() {
                try {
                    getData(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        handler.post(runnable);

        //상세조건 검색
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //시작하자마자 모든 마우스가 잇는 리스트 출력하고 상세조건 검색으로 변형할것
                (new Thread() {
                    public void run() {
                        adapter.clear();
                        selectlist.addAll(mylist);
                        isTrue=false;
                        try {
                            checkUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                handler.post(runnable);
                Toast.makeText(MouseActivity.this, "검색완료", Toast.LENGTH_SHORT).show();
            }
        });
        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check==0) {
                    OptionList.setVisibility(View.INVISIBLE);
                    //OptionList.findViewById(R.id.checker);
                    btnHide.setText("펼치기");
                    check = 1;
                    Toast.makeText(MouseActivity.this, "펼치기", Toast.LENGTH_SHORT).show();
                }
                else {
                    OptionList.setVisibility(View.VISIBLE);
                    //OptionList.findViewById(R.id.checker2);
                    btnHide.setText("접기");
                    check = 0;
                    Toast.makeText(MouseActivity.this, "접기", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MouseActivity.this, ""+mylist.get(position).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mylist.get(position).getSite()));
                startActivity(intent);
            }
        });

        imgbuttonListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.homebtn:
                        Intent intent4 = new Intent(MouseActivity.this,GridActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.camerabtn:
                        Intent intent5 = new Intent(MouseActivity.this,Camera1Activity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.healthbtn:
                        Intent intent6 = new Intent(MouseActivity.this,HealthActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.settingbtn:
                        Intent intent8 = new Intent(MouseActivity.this,SettingActivity.class);
                        startActivity(intent8);
                        finish();
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

    public int[] cbtCheck(){
        int arr[] = new int[6];
        if(wire1Btn.isChecked()) arr[0] = 1;
        if(wire2Btn.isChecked()) arr[1] = 1;
        if(wire3Btn.isChecked()) arr[2] = 1;
        if(weight1Btn.isChecked()) arr[3] = 1;
        if(weight2Btn.isChecked()) arr[4] = 1;
        if(weight3Btn.isChecked()) arr[5] = 1;

        return arr;
    }

    public void checkUpdate(){
        int check[] = cbtCheck();
        int i, j;
        for(i=0; i<6;i++){
            j=0;
            if(check[i]==0) {
                if(i==0) {
                    while (j<selectlist.size()) {
                        if (selectlist.get(j).getWire().equals("1"))
                            selectlist.remove(j);
                        else j++;
                    }
                }
                else if(i==1) {
                    while (j<selectlist.size()) {
                        if (selectlist.get(j).getWire().equals("0"))
                            selectlist.remove(j);
                        else j++;
                    }
                }
                else if(i==2) {
                    while (j<selectlist.size()) {
                        if (selectlist.get(j).getWire().equals("2"))
                            selectlist.remove(j);
                        else j++;
                    }
                }
                else if(i==3) {
                    while (j<selectlist.size()) {
                        if (selectlist.get(j).getIntweight() <= 90)
                            selectlist.remove(j);
                        else j++;
                    }
                }
                else if(i==4) {
                    while (j<selectlist.size()) {
                        if (selectlist.get(j).getIntweight() > 90 && selectlist.get(j).getIntweight() <= 120)
                            selectlist.remove(j);
                        else j++;
                    }
                }
                else {
                    while (j<selectlist.size()) {
                        if (selectlist.get(j).getIntweight() > 120)
                            selectlist.remove(j);
                        else j++;
                    }
                }
            }
        }
        if(i==6)
            isTrue=true;
    }

    // 리스트뷰에 들어갈 아이템 추가
    private ArrayList<search_item> addItemToList() {
        ArrayList<search_item> mouseList = new ArrayList<search_item>();
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            jdata  = jsonObj.getJSONArray("result");
            for(int i=0;i<jdata.length();i++){
                JSONObject c = jdata.getJSONObject(i);
                String name = c.getString(Tag_name);
                String length = c.getString(Tag_length);
                String width = c.getString(Tag_width);
                String height = c.getString(Tag_height);
                String weight = c.getString(Tag_weight);
                String wire = c.getString(Tag_wire);
                String picture = c.getString(Tag_picture);
                String site = c.getString(Tag_site);

                search_item data = new search_item();
                data.setName(name);
                data.setLength(length);
                data.setWidth(width);
                data.setHeight(height);
                data.setWeight(weight);
                data.setWire(wire);
                data.setPicture(picture);
                data.setSite(site);
                mouseList.add(data);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        isTrue=true;
        return mouseList;
    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected  String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine()) != null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e){
                    return  null;
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSON = result;
                mylist = addItemToList();
                selectlist = addItemToList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}