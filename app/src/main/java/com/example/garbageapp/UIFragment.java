package com.example.garbageapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.garbageapp.utility.GarbageViewModel;
import com.example.garbageapp.utility.Item;
import com.example.garbageapp.utility.ItemsDB;


public class UIFragment extends Fragment {

//    private static ItemsDB itemsDB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        itemsDB = ItemsDB.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_ui, container, false);
        final GarbageViewModel viewModel = new ViewModelProvider(requireActivity()).get(GarbageViewModel.class);
        viewModel.awaitInit();

        EditText itemWhat = v.findViewById(R.id.editTexUi);

        Button whereBtn = v.findViewById(R.id.where_btn);
        Button addItemBtn = v.findViewById(R.id.add_item_btn);
        Button deleteBtn = v.findViewById(R.id.delete_btn);
        Button showListBtn = v.findViewById(R.id.showListBtn);

        whereBtn.setOnClickListener(view->{
            viewModel.onFindItemClick(itemWhat);
        });

        deleteBtn.setOnClickListener(view ->{
            viewModel.onDeleteItemBtnClick(itemWhat, getActivity());
        });

        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_UIFragment_to_ListFragment);
            }
        });


        addItemBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_UIFragment_to_AddItemFragment);
        });


//        addItemBtn.setOnClickListener(view ->{
//            Intent intent = new Intent(requireActivity(), AddItemActivity.class);
//            startActivity(intent);
//        });


//        return inflater.inflate(R.layout.fragment_ui, container, false);
        return v;
    }
}