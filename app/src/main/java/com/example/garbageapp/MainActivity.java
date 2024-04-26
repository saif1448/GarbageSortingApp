package com.example.garbageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.garbageapp.utility.ItemsDB;

public class MainActivity extends AppCompatActivity {

    private static ItemsDB itemsDB = ItemsDB.get();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button whereBtn = findViewById(R.id.where_btn);
        Button addItemBtn = findViewById(R.id.add_item_btn);
        whereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText itemText = findViewById(R.id.editTxt);
                String itemName = itemText.getText().toString();
                String place = itemName+ " should be placed in: "+itemsDB.returnPlace(itemName.toLowerCase());
                itemText.setText(place);
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });


    }
}