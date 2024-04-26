package com.example.garbageapp.utility;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private static List<Item> itemList = new ArrayList<>();
    static {
        itemList.add(new Item("milk carton", "Food"));
    }
    public static String returnPlace(String name){
        for(Item item: itemList){
            if(item.getName().equals(name)){
                return item.getPlace();
            }
        }
        return "not found";
    }

}
