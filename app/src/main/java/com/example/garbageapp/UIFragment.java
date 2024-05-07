package com.example.garbageapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.garbageapp.utility.Item;
import com.example.garbageapp.utility.ItemsDB;


public class UIFragment extends Fragment {

    private static ItemsDB itemsDB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_ui, container, false);
//        final Item item = new ViewModelProvider(requireActivity()).get(Item.class);

        TextView itemWhat = v.findViewById(R.id.editTexUi);

        Button whereBtn = v.findViewById(R.id.where_btn);
//        Button addItemBtn = v.findViewById(R.id.add_item_btn);

        whereBtn.setOnClickListener(view -> {
            String itemName = itemWhat.getText().toString();
            String place = itemName+ " should be placed in: "+itemsDB.returnPlace(itemName.toLowerCase());
            itemWhat.setText(place);
        });

//        addItemBtn.setOnClickListener(view ->{
//            Intent intent = new Intent(UIFragment.this, AddItemActivity.class);
//            startActivity(intent);
//        });


//        return inflater.inflate(R.layout.fragment_ui, container, false);
        return v;
    }
}