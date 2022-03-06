package com.example.twonumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.twonumbers.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ListViewModel lvModel;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        lvModel = new ViewModelProvider(this).get(ListViewModel.class);
        arrayList = (ArrayList<String>) lvModel.getList().getValue();
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        binding.lvHistory.setAdapter(arrayAdapter);

        lvModel.getList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                arrayList = (ArrayList<String>) strings;
            }
        });
    }

    public void calculator(View view) {
        int a = Integer.parseInt(binding.edtA.getText().toString());
        int b = Integer.parseInt(binding.edtB.getText().toString());
        if (view.getId() == binding.btnPlus.getId())
            lvModel.addToList(a + " + " + b + " = " + (a+b));
        if (view.getId() == binding.btnMinus.getId())
            lvModel.addToList(a + " - " + b + " = " + (a-b));
        if (view.getId() == binding.btnMultiply.getId())
            lvModel.addToList(a + " * " + b + " = " + (a*b));
        if (view.getId() == binding.btnDivide.getId())
            lvModel.addToList(a + " / " + b + " = " + (a/b));
        arrayAdapter.notifyDataSetChanged();
    }
}