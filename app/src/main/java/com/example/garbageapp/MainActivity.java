package com.example.garbageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.garbageapp.utility.GarbageViewModel;
import com.example.garbageapp.utility.ItemsDB;

public class MainActivity extends AppCompatActivity {

//    private static ItemsDB itemsDB;

    private GarbageViewModel list;
    Fragment fragmentUI, fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ViewModelProvider(this).get(GarbageViewModel.class);
//        list.awaitInit();

    }

//    private void setUpFragments(){
//        FragmentManager fm = getSupportFragmentManager();
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            fragmentUI = fm.findFragmentById(R.id.container_ui);
//            fragmentList = fm.findFragmentById(R.id.container_list);
//            if(fragmentUI == null && fragmentList == null){
//                fragmentUI = new UIFragment();
//                fragmentList = new ListFragment();
//                fm.beginTransaction()
//                        .add(R.id.container_ui, fragmentUI)
//                        .add(R.id.container_list, fragmentList)
//                        .commit();
//
//            }
//        }else{
//            if(fragmentUI == null){
//                fragmentUI = new UIFragment();
//                fm.beginTransaction()
//                        .add(R.id.container_ui, fragmentUI)
//                        .commit();
//            }
//        }
//
//    }
}