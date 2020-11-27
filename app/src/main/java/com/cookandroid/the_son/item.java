package com.cookandroid.the_son;

public class item {
    private String name, length, width, height, weight;

    public item(){
    }
    public item(String name, String length, String width, String height, String weight){
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public String getName(){ return name; }
    public String getLength(){ return length; }
    public String getWidth(){ return width; }
    public String getHeight(){ return height; }
    public String getWeight(){ return weight; }

    public void setName(String name){ this.name = name; }
    public void setLength(String length){ this.length= length; }
    public void setWidth(String width){ this.width = width; }
    public void setHeight(String height){ this.height = height; }
    public void setWeight(String weight){ this.weight = weight; }
}
