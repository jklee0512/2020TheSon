package com.cookandroid.the_son;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

class ListViewAdapter extends BaseAdapter {
    private ArrayList<search_item> items;
    private Context context;
    public ListViewAdapter(Context context){
        this.context = context;
        this.items = new ArrayList<search_item>();
    }
    public ListViewAdapter(Context context, ArrayList<search_item> items){
        this.context = context;
        this.items = items;
    }
    public void clear() { items.clear(); }
    public void addItem(search_item item){
        items.add(item);
    }
    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView view = new ItemView(context);
        search_item item = items.get(position);
        view.setImageV(context, item.getPicture());
        view.setNameV(item.getName());
        view.setlengthV(item.getLength());
        view.setwidthV(item.getWidth());
        view.setheightV(item.getHeight());
        view.setweightV(item.getWeight());
        return view;
    }
}