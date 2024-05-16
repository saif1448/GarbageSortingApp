package com.example.garbageapp.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ItemsDB {
//    private static ItemsDB sItemsDB;
    private final static String GarbageURL = "https://garbageserver.onrender.com/";

    private List<Item> values;
    private final Semaphore init = new Semaphore(0);


    public ItemsDB(){
        values = new ArrayList<>();
        networkDB(GarbageURL, "", values);
    }

    public void awaitInit() {
        if (values.size() == 0)
            try {
                init.acquire();
            } catch (InterruptedException ie) {
            }
    }

    private void networkDB(String url, String command, List<Item> values) {
        Runnable r = new HttpThread(url + command, values, init);
        new Thread(r).start();
    }

    public synchronized void addItem(String what, String where) {
        values.add(new Item(what, where));
        networkDB(GarbageURL, "?op=insert"+ "&what=" + what + "&whereC=" + where, values);
    }

    public synchronized void removeItem(String what) {
        // Should delete all rows in values similarly to what happens in database
        for (Item t : values) {
            if (t.getName().equals(what)) {
                values.remove(t);
                networkDB(GarbageURL, "?op=remove"+ "&what=" + what, values);
                break;
            }
        }
    }

    public synchronized int size() {
        return values.size();
    }

    public synchronized List<Item> getValues() {
        return values;
    }

//    public static ItemsDB get() {
//        if (sItemsDB == null) sItemsDB= new ItemsDB();
//        return sItemsDB;
//    }

    public String returnPlace(String name){
        String place = "";
            for (Item t : values) {
                if (t.getName().equals(name)) {
                    place = t.getPlace();
                    break;
                }
            }
        return place;
    }



//    public HashMap<String, String> getItemsList() {
//        return itemList;
//    }
//    public String returnPlace(String name){
//        return itemList.getOrDefault(name, "not found");
//    }
//
//    public void addItem(Item newItem){
//        itemList.put(newItem.getName(), newItem.getPlace());
//    }
//
//    public void removeItem(String what){
//        itemList.remove(what);
//    }
//
//    public boolean isPresent(String what) {
//        return itemList.get(what) != null;
//    }
//
//    public String getWhere(String what) {
//        return itemList.get(what);
//    }
//
//    public String listItems(){
//        String r = "";
//        for(HashMap.Entry<String, String> item: itemList.entrySet()){
//            r += item.getKey() + " in "+ item.getValue() + "\n";
//        }
//
//        return r;
//    }
//
//    public List<Item> convertedToListItems(){
//        List<Item> mapToListItems = new ArrayList<>();
//        for(HashMap.Entry<String, String> item: itemList.entrySet()){
//            Item i = new Item(item.getKey(), item.getValue());
//            mapToListItems.add(i);
//        }
//        return mapToListItems;
//    }
//
//    public void fillItemsDB(){
//        itemList.put("newspaper", "paper");
//        itemList.put("magazine", "paper");
//        itemList.put("milk carton,", "food");
//        itemList.put("shoe box", "cardboard");
//        itemList.put("can", "metal");
//        itemList.put("book", "paper");
//    }

}
