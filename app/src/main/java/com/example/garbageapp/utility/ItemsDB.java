package com.example.garbageapp.utility;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private List<Item> itemList = new ArrayList<>();
    private ItemsDB(){
        fillItemsDB();
    }
    public static ItemsDB get() {
        if (sItemsDB == null) sItemsDB= new ItemsDB();
        return sItemsDB;
    }
    public String returnPlace(String name){
        for(Item item: itemList){
            if(item.getName().equals(name)){
                return item.getPlace();
            }
        }
        return "not found";
    }

    public void addItem(Item newItem){
        itemList.add(newItem);
    }

    public void fillItemsDB() {
        itemList.add(new Item("book1", "paper"));
        itemList.add(new Item("book2", "paper"));
        itemList.add(new Item("can", "metal"));
        itemList.add(new Item("fish", "bio"));
        itemList.add(new Item("meat", "bio"));
    }

}
