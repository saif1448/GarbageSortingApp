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

public class GarbageViewModel extends ViewModel {
    private static final ItemsDB itemsDB = ItemsDB.get();

    private final MutableLiveData<GarbageUiState> uiState =
            new MutableLiveData<>(new GarbageUiState(itemsDB.listItems()));

    public LiveData<GarbageUiState> getUiState(){
        return uiState;
    }

    public void onAddItemClick(TextView itemWhat, TextView itemWhere, FragmentActivity activity) {
        String whatS = itemWhat.getText().toString().trim();
        String whereS = itemWhere.getText().toString().trim();
        if ((whatS.length() > 0) && (whereS.length() > 0)) {
            itemsDB.addItem(new Item(whereS, whereS));
            itemWhat.setText("");
            itemWhere.setText("");
            itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
            itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
            uiState.setValue(new GarbageUiState(itemsDB.listItems()));
        } else
            showToast(activity);
    }

    public void onDeleteItemClick(TextView itemWhat, FragmentActivity activity) {
        String what = itemWhat.getText().toString().trim();
        if (!what.isEmpty() && itemsDB.isPresent(what)) {
            itemsDB.removeItem(itemWhat.getText().toString());
            uiState.setValue(new GarbageUiState(itemsDB.listItems()));
            showToast(activity, "Removed " + itemWhat.getText());
        } else showToast(activity, itemWhat.getText() + " not found");
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
        public final String listItems;
        public GarbageUiState(String listItems) {
            this.listItems = listItems;
        }
    }
}
