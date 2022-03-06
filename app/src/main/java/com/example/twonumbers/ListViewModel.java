package com.example.twonumbers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
    private MutableLiveData<List<String>> list;
    public LiveData<List<String>> getList() {
        if (list == null) {
            list = new MutableLiveData<List<String>>();
            list.setValue(new ArrayList<>());
        }
        return list;
    }

    public void addToList(String string) {
        List<String> array = list.getValue();
        array.add(string);
        list.setValue(array);
    }
}
