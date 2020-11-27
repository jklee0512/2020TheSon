package com.cookandroid.the_son;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class PopMouse extends AppCompatActivity  {

    AlertDialog dialog;

    private Button btn1, btn2;
    private EditText text;
    private ListView listview;

    String mdata1="";
    String mdata2="";
    String myJSON;

    private static final String Tag_name = "mouse_name";
    private static final String Tag_length = "mouse_length";
    private static final String Tag_width = "mouse_width";
    private String url = "http://121.190.18.135/mouse_search.php";

    JSONArray jdata = null;
    ArrayList<HashMap<String, String>> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_mouse);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) width, (int) (height * 0.8));

        btn1 = (Button) findViewById(R.id.findbtn);
        btn2 = (Button) findViewById(R.id.returnbtn);
        text = (EditText) findViewById(R.id.edit_mouse);
        listview = (ListView) findViewById(R.id.findmouse_list);
        datalist = new ArrayList<HashMap<String, String>>();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datalist.clear();
                getData(url);
                }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("mouse_width",mdata1);
                intent.putExtra("mouse_length",mdata2);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mdata1 = datalist.get(position).get("mouse_width");
                mdata2 = datalist.get(position).get("mouse_length");
                Toast.makeText(PopMouse.this, ""+datalist.get(position).get("mouse_name"),Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void showList(String str){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            jdata  = jsonObj.getJSONArray("result");
            for(int i=0;i<jdata.length();i++){
                JSONObject c = jdata.getJSONObject(i);
                String name = c.getString(Tag_name);
                if(name.indexOf(str) < 0) continue;
                String H = c.getString(Tag_length);
                String W = c.getString(Tag_width);

                HashMap<String, String> data = new HashMap<String, String>();
                data.put(Tag_name, name);
                data.put(Tag_length,H);
                data.put(Tag_width, W);

                datalist.add(data);
            }
            ListAdapter adapter = new SimpleAdapter(
                    PopMouse.this, datalist, R.layout.list_item, new String[]{Tag_name,Tag_length,Tag_width}, new int[]{R.id.name,R.id.height,R.id.width}
            );
            listview.setAdapter(adapter);
            listview.setVisibility(View.VISIBLE);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String>{
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
                showList(text.getText().toString());
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
