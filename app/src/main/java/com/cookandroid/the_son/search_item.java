package com.cookandroid.the_son;

public class search_item {

    private String name;
    private String length;
    private String width;
    private String height;
    private String weight;
    private String wire;
    private String picture;
    private String site;
    private int Intweight=0;

    search_item(){
    }

    public search_item(String name, String length, String width, String height, String weight, String wire, String picture, String site){
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.wire = wire;
        this.picture = picture;
        this.site = site;
    }

    public String getName(){ return name; }
    public String getLength(){ return length; }
    public String getWidth(){ return width; }
    public String getHeight(){ return height; }
    public String getWeight(){ return weight; }
    public String getWire(){ return wire; }
    public String getPicture(){ return picture; }
    public String getSite(){ return site; }
    public int getIntweight() { return Intweight; }

    public void setName(String name){ this.name = name; }
    public void setLength(String length){ this.length= length; }
    public void setWidth(String width){ this.width = width; }
    public void setHeight(String height){ this.height = height; }
    public void setWeight(String weight){
        this.weight = weight;
        this.Intweight = Integer.parseInt(weight);
    }
    public void setWire(String wire){ this.wire = wire; }
    public void setPicture(String picture){ this.picture = picture; }
    public void setSite(String site) {this.site = site; }
}
