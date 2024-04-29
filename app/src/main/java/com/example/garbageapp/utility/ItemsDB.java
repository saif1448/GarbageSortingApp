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
    private static Context context;
    private final HashMap<String, String> itemList = new HashMap<>();
    private ItemsDB(){
        if (context == null)
            throw new IllegalStateException("context must be set first: use setContext(..)");
        fillItemsDB(context, "garbage.txt");
    }

    public static ItemsDB get() {
        if (sItemsDB == null) sItemsDB= new ItemsDB();
        return sItemsDB;
    }

    public static void setContext(Context aContext) {
        context = aContext;
    }

    public String returnPlace(String name){
        return itemList.getOrDefault(name, "not found");
    }

    public void addItem(Item newItem){
        itemList.put(newItem.getName(), newItem.getPlace());
    }

    public void fillItemsDB(Context context, String filename) {
        try {
            BufferedReader reader= new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));
            String line= reader.readLine();
            while (line != null) {
                String[] gItem= line.split(",");
                itemList.put(gItem[0], gItem[1]);
                line= reader.readLine();
            }
        } catch (IOException e) {  // Error occurred when opening raw file for reading.
        }
    }

}
