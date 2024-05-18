package com.example.garbageapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.garbageapp.utility.GarbageViewModel;

public class AddItemFragment extends Fragment {

    GarbageViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_add_item, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(GarbageViewModel.class);
        viewModel.awaitInit();

        Button addItemButton = v.findViewById(R.id.add_item_button);
        EditText what = v.findViewById(R.id.what_text);
        EditText where = v.findViewById(R.id.where_text);
        String whatS = what.getText().toString().trim();
        String whereS = where.getText().toString().trim();
        addItemButton.setOnClickListener(view ->{
            viewModel.onAddItemClick(what, where, getActivity());
        });

        Button backBtnAddI = v.findViewById(R.id.backBtnAddI);
        backBtnAddI.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_AddItemFragment_to_UIFragment);
        });

        return v;
    }
}