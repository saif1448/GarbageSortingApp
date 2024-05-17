package com.example.garbageapp.utility;

import android.graphics.Color;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.garbageapp.R;

import java.util.List;

public class GarbageViewModel extends ViewModel {
    private static final ItemsDB itemsDB = ItemsDB.get();

    private final MutableLiveData<GarbageUiState> uiState =
//            new MutableLiveData<>(new GarbageUiState(itemsDB.listItems()));
    new MutableLiveData<>(new GarbageUiState(itemsDB.convertedToListItems(), itemsDB.convertedToListItems().size()));

    public LiveData<GarbageUiState> getUiState(){
        return uiState;
    }

    public void onAddItemClick(EditText itemWhat, FragmentActivity activity) {
        String whatS = itemWhat.getText().toString().trim();
        String whereS = itemsDB.getWhere(whatS);
        if ((whatS.length() > 0) && (whereS.length() > 0)) {
            itemsDB.addItem(new Item(whereS, whereS));
            itemWhat.setText("");
            itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
//            uiState.setValue(new GarbageUiState(itemsDB.listItems()));
            uiState.setValue(new GarbageUiState(itemsDB.convertedToListItems(), itemsDB.convertedToListItems().size()));
        } else
            showToast(activity);
    }

//    private boolean containsItem(List<Item> itemList, String what) {
//        for (Item item : itemList) {
//            if (item.getName().equals(what)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public void onDeleteItemClick(TextView itemWhat, FragmentActivity activity) {
        String what = itemWhat.getText().toString().trim();
        if (!what.isEmpty() && itemsDB.isPresent(what)) {
            itemsDB.removeItem(what);
//            uiState.setValue(new GarbageUiState(itemsDB.listItems()));
            uiState.setValue(new GarbageUiState(itemsDB.convertedToListItems(), itemsDB.convertedToListItems().size()));
            showToast(activity, "Removed " + what);
        } else showToast(activity, what + " not found");
    }

    public void onDeleteItemClick(String what, FragmentActivity activity) {
        String message;
        if (itemsDB.isPresent(what)) {
            itemsDB.removeItem(what);
//            uiState.setValue(new GarbageUiState(itemsDB.listItems()));
            uiState.setValue(new GarbageUiState(itemsDB.convertedToListItems(), itemsDB.convertedToListItems().size()));
            message = "Removed " + what;
        } else {
            message = what + " not found";
        }
        showToast(activity, message);
    }

    public void onFindItemClick(TextView itemWhat, TextView itemWhere) {
        String what = itemWhat.getText().toString().trim();
        itemWhat.setBackgroundColor(Color.parseColor("#FFFFFF"));
        itemWhere.setText(itemsDB.getWhere(what));
        itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
        itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

    public void onFindItemClick(EditText itemWhere) {
        String what = itemWhere.getText().toString().trim();
        itemWhere.setText(what + " should be placed in: "+itemsDB.returnPlace(what));
        itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

    private void showToast(FragmentActivity activity) {
        Toast.makeText(activity, R.string.empty_toast, Toast.LENGTH_LONG).show();
    }

    private void showToast(FragmentActivity activity, CharSequence message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
    public static class GarbageUiState {
//        public final String listItems;
        public final List<Item> listItems;
        public int itemListSize;
//        public GarbageUiState(String listItems) {
//            this.listItems = listItems;
//        }
        public GarbageUiState(List<Item> listItems, int itemListSize){
            this.listItems = listItems;
            this.itemListSize = itemListSize;
        }
    }
}
