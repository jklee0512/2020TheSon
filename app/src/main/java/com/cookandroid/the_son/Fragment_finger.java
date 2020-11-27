package com.cookandroid.the_son;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_finger extends Fragment {

    private TextView text1, text2, text3, text4, text5, text6;
    private ImageView img1, img2, img3, img4, img5, img6;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.health_finger, container, false);

        return view;
    }

}
