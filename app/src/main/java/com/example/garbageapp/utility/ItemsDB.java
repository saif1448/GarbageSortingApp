package com.example.garbageapp.utility;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemsDB {
    private static ItemsDB sItemsDB;
//    private static Context context;
    private final HashMap<String, String> itemList = new HashMap<>();
    private ItemsDB(){
//        if (context == null)
//            throw new IllegalStateException("context must be set first: use setContext(..)");
//        fillItemsDB(context, "garbage.txt");
        fillItemsDB();
    }

    public static ItemsDB get() {
        if (sItemsDB == null) sItemsDB= new ItemsDB();
        return sItemsDB;
    }

//    public static void setContext(Context aContext) {
//        context = aContext;
//    }

    public HashMap<String, String> getItemsDB() {
        return itemList;
    }
    public String returnPlace(String name){
        return itemList.getOrDefault(name, "not found");
    }

    public void addItem(Item newItem){
        itemList.put(newItem.getName(), newItem.getPlace());
    }

    public void removeItem(String what){
        itemList.remove(what);
    }

    public boolean isPresent(String what) {
        return itemList.get(what) != null;
    }

    public String getWhere(String what) {
        return itemList.get(what);
    }

    public String listItems(){
        String r = "";
        for(HashMap.Entry<String, String> item: itemList.entrySet()){
            r += item.getKey() + " in "+ item.getValue() + "\n";
        }

        return r;
    }

//    public void fillItemsDB(Context context, String filename) {
//        try {
//            BufferedReader reader= new BufferedReader(
//                    new InputStreamReader(context.getAssets().open(filename)));
//            String line= reader.readLine();
//            while (line != null) {
//                String[] gItem= line.split(",");
//                itemList.put(gItem[0], gItem[1]);
//                line= reader.readLine();
//            }
//        } catch (IOException e) {  // Error occurred when opening raw file for reading.
//        }
//    }

    public void fillItemsDB(){
        itemList.put("newspaper", "paper");
        itemList.put("magazine", "paper");
        itemList.put("milk carton,", "food");
        itemList.put("shoe box", "cardboard");
        itemList.put("can", "metal");
        itemList.put("book", "paper");
    }

}
