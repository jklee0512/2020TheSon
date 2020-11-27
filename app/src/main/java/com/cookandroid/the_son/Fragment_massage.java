package com.cookandroid.the_son;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_massage extends Fragment {

    private TextView text1, text2, text3;
    private ImageView img1, img2, img3;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.health_massage,container, false);

        return view;
    }

}