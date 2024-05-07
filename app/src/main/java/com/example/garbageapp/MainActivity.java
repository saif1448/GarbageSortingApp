package com.example.garbageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.garbageapp.utility.ItemsDB;

public class MainActivity extends AppCompatActivity {

    private static ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemsDB.setContext(MainActivity.this);
        itemsDB = ItemsDB.get();
        setUpFragments();
    }

    private void setUpFragments(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentUI = fm.findFragmentById(R.id.container_ui);
        Fragment fragmentList = fm.findFragmentById(R.id.container_list);
        if(fragmentUI == null && fragmentList == null){
            fragmentUI = new UIFragment();
            fragmentList = new ListFragment();
            fm.beginTransaction()
                    .add(R.id.container_ui, fragmentUI)
                    .add(R.id.container_list, fragmentList)
                    .commit();

        }
    }
}