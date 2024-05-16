package com.example.garbageapp.utility;

//import static com.example.garbageapp.utility.ItemsDB.INITIALS;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NetworkFetcher {
    private static final String TAG = "NetworkFetchr";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public void fetchItems(String url, List<Item> values) {
        try {
            parseItems(new String(getUrlBytes(url)), values);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
    }

    private void parseItems(String jsonString, List<Item> values) throws IOException, JSONException {
        if (jsonString.length() > 0) {
            JSONArray itemsA = new JSONArray(jsonString);
            System.out.println(itemsA);
            for (int i = 0; (i < itemsA.length()); i++) {
//                if (itemsA.getJSONObject(i).getString("who").equals(INITIALS)) //Filter items on INITIALS
                values.add(new Item(itemsA.getJSONObject(i).getString("what"),
                            itemsA.getJSONObject(i).getString("whereC")));
            }

            System.out.println(values);
        }
    }
}
