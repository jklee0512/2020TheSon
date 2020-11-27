package com.cookandroid.the_son;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemView extends LinearLayout {
    ImageView imgV;
    TextView nameV, lengthV, widthV, heightV, weightV;

    public ItemView(Context context) {
        super(context);
        init(context);
    }

    public ItemView(Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_mouse, this, true);

        imgV =(ImageView) findViewById(R.id.mListimg);
        nameV=(TextView) findViewById(R.id.mListname);
        lengthV=(TextView) findViewById(R.id.mListW2);
        widthV=(TextView) findViewById(R.id.mListH2);
        heightV=(TextView) findViewById(R.id.mListT2);
        weightV=(TextView) findViewById(R.id.mListG2);

    }

    public void setImageV(Context context, String str){ Glide.with(context).load(str).into(imgV); }
    public void setNameV(String str){
        nameV.setText(str);
    }
    public void setlengthV(String str){ lengthV.setText(str); }
    public void setwidthV(String str){ widthV.setText(str); }
    public void setheightV(String str){
        heightV.setText(str);
    }
    public void setweightV(String str){
        weightV.setText(str);
    }
}
