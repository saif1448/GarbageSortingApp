package com.example.garbageapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.garbageapp.utility.GarbageViewModel;
import com.example.garbageapp.utility.ItemsDB;
import com.example.garbageapp.utility.Item;


public class ListFragment extends Fragment {

    private static ItemsDB itemsDB;
     GarbageViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_list, container, false);
//        final TextView listThings = v.findViewById(R.id.listItems);

        viewModel = new ViewModelProvider(requireActivity()).get(GarbageViewModel.class);


//        String listThingsTxt = itemsDB.listItems();
//        viewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> listThings.setText(uiState.listItems));
//        listThings.setText(listThingsTxt);

        RecyclerView itemList = v.findViewById(R.id.listItems);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemAdapter mAdapter = new ItemAdapter();
        itemList.setAdapter(mAdapter);

        viewModel.getUiState().observe(getActivity(), itemsDB -> mAdapter.notifyDataSetChanged());



        return  v;

    }


    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mWhatTextView, mWhereTextView, mNoView;

        public ItemHolder(View itemView) {
            super(itemView);
            mNoView = itemView.findViewById(R.id.item_no);
            mWhatTextView = itemView.findViewById(R.id.item_what);
            mWhereTextView = itemView.findViewById(R.id.item_where);
            itemView.setOnClickListener(this);
        }

        public void bind(Item item, int position) {
            mNoView.setText(" " + position + " ");
            mWhatTextView.setText(item.getName());
            mWhereTextView.setText(item.getPlace());
        }

        @Override
        public void onClick(View v) {
            // Trick from https://stackoverflow.com/questions/5754887/accessing-view-inside-the-linearlayout-with-code
            String what = (String) ((TextView) v.findViewById(R.id.item_what)).getText();
            //once we have a value for what, we can delete the item
            viewModel.onDeleteItemClick(what, getActivity());
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.one_row, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = viewModel.getUiState().getValue().listItems.get(position);
            holder.bind(item, position);
        }

        @Override
        public int getItemCount() {
            return viewModel.getUiState().getValue().itemListSize;
        }
    }
}