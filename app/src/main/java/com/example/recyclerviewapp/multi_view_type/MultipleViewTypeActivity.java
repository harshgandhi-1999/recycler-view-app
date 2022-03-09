package com.example.recyclerviewapp.multi_view_type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.MultiEmployee;

import java.util.ArrayList;

public class MultipleViewTypeActivity extends AppCompatActivity {

    private RecyclerView multiViewRecView;
    private MultiViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_view_type);

        multiViewRecView = findViewById(R.id.multiViewRecView);
        adapter = new MultiViewAdapter(this);
        multiViewRecView.setAdapter(adapter);

        multiViewRecView.setLayoutManager(new LinearLayoutManager(this));
        multiViewRecView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        addData();
    }

    private void addData() {
        ArrayList<MultiEmployee> employees = new ArrayList<>();

        employees.add(new MultiEmployee("emp1","abc","123","emp1@gmail.com"));
        employees.add(new MultiEmployee("emp2","def","456",""));

        adapter.setEmployees(employees);
    }
}