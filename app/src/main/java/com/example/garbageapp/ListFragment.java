package com.example.garbageapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.garbageapp.utility.GarbageViewModel;
import com.example.garbageapp.utility.ItemsDB;


public class ListFragment extends Fragment {

    private static ItemsDB itemsDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_list, container, false);
        final TextView listThings = v.findViewById(R.id.listItems);
        final GarbageViewModel viewModel = new ViewModelProvider(requireActivity()).get(GarbageViewModel.class);


        String listThingsTxt = itemsDB.listItems();
        viewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> listThings.setText(uiState.listItems));
        listThings.setText(listThingsTxt);
        return  v;

    }
}