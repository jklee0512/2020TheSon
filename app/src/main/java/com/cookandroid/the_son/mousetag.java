package com.cookandroid.the_son;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class mousetag extends AppCompatActivity {

    private Button okbtn;
    private CheckBox Galaxy, Leopold, Samsung, Zenix, Hansung, Abko;
    private CheckBox Apple, Asus, Cherry, Cox, Cosair, Dell, Hacker;
    private CheckBox GIgabyte, Gskill, Hp, Lg, Logitech, Maxtill, Msi, Razer;
    private CheckBox wire1Btn, wire2Btn, wire3Btn, weight1Btn, weight2Btn, weight3Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mousetag);

        okbtn = findViewById(R.id.okbtn);
        Galaxy = findViewById(R.id.galaxy);
        Leopold = findViewById(R.id.leopold);
        Samsung = findViewById(R.id.samsung);
        Zenix = findViewById(R.id.zenix);
        Hansung = findViewById(R.id.hansung);
        Abko = findViewById(R.id.abko);
        Apple = findViewById(R.id.apple);
        Asus = findViewById(R.id.asus);
        Cherry = findViewById(R.id.cherry);
        Cox = findViewById(R.id.cox);
        Cosair = findViewById(R.id.cosair);
        Dell = findViewById(R.id.dell);
        GIgabyte = findViewById(R.id.gigabyte);
        Gskill = findViewById(R.id.gskill);
        Hp = findViewById(R.id.hp);
        Lg = findViewById(R.id.lg);
        Logitech =findViewById(R.id.logitech);
        Maxtill = findViewById(R.id.maxtill);
        Msi = findViewById(R.id.msi);
        Razer = findViewById(R.id.razer);
        Hacker = findViewById(R.id.hacker);
        wire1Btn = findViewById(R.id.wire1);
        wire2Btn = findViewById(R.id.wire2);
        wire3Btn = findViewById(R.id.wire3);
        weight1Btn = findViewById(R.id.weight1);
        weight2Btn = findViewById(R.id.weight2);
        weight3Btn = findViewById(R.id.weight3);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("array",cbtCheck());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
    public int[] cbtCheck(){
        int arr[] = new int[27];
        if(wire1Btn.isChecked()) arr[0] = 1;
        if(wire2Btn.isChecked()) arr[1] = 1;
        if(wire3Btn.isChecked()) arr[2] = 1;
        if(weight1Btn.isChecked()) arr[3] = 1;
        if(weight2Btn.isChecked()) arr[4] = 1;
        if(weight3Btn.isChecked()) arr[5] = 1;
        if(Galaxy.isChecked()) arr[6]=1;
        if(Leopold.isChecked()) arr[7]=1;
        if(Samsung.isChecked()) arr[8]=1;
        if(Zenix.isChecked()) arr[9]=1;
        if(Hansung.isChecked()) arr[10]=1;
        if(Abko.isChecked()) arr[11]=1;
        if(Apple.isChecked()) arr[12]=1;
        if(Asus.isChecked()) arr[13]=1;
        if(Cherry.isChecked()) arr[14]=1;
        if(Cox.isChecked()) arr[15]=1;
        if(Cosair.isChecked()) arr[16]=1;
        if(Dell.isChecked()) arr[17]=1;
        if(GIgabyte.isChecked()) arr[18]=1;
        if(Gskill.isChecked()) arr[19]=1;
        if(Hp.isChecked()) arr[20]=1;
        if(Lg.isChecked()) arr[21]=1;
        if(Logitech.isChecked()) arr[22]=1;
        if(Maxtill.isChecked()) arr[23]=1;
        if(Msi.isChecked()) arr[24]=1;
        if(Razer.isChecked()) arr[25]=1;
        if(Hacker.isChecked()) arr[26]=1;

        return arr;
    }

}
