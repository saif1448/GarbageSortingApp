package com.example.garbageapp.utility;

public class Item {
    private String name;
    private String place;

    public Item(String name, String place){
        this.name=name;
        this.place=place;
    }

    public String getName(){
        return name;
    }
    public String getPlace(){
        return place;
    }
}
