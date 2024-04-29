package com.example.garbageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.garbageapp.utility.Item;
import com.example.garbageapp.utility.ItemsDB;

public class AddItemActivity extends AppCompatActivity {

    private static ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemsDB = ItemsDB.get();

        Button addNewBtn = findViewById(R.id.add_new_item_btn);

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText whatText = findViewById(R.id.what_text);
                EditText whereText = findViewById(R.id.where_text);

                String whatTextValue = whatText.getText().toString();
                String whereTextValue = whereText.getText().toString();

                Item newItem = new Item(whatTextValue, whereTextValue);
                itemsDB.addItem(newItem);
                Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}