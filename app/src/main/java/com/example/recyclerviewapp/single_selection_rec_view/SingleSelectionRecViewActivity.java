package com.example.recyclerviewapp.single_selection_rec_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.Employee;

import java.util.ArrayList;

public class SingleSelectionRecViewActivity extends AppCompatActivity {

    private SingleAdapter adapter;
    private ArrayList<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection_rec_view);

        RecyclerView recyclerView = findViewById(R.id.singleSelectionRecView);
        adapter = new SingleAdapter(this);
        Button btnGetSelectedItem = findViewById(R.id.btnGetSelectedItem);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        addData();

        adapter.setEmployees(employees);

        btnGetSelectedItem.setOnClickListener(view ->{
            if(adapter.getSelectedEmployee()!=null){
                showToast(adapter.getSelectedEmployee().getName());
            }else{
                showToast("No selection");
            }
        });
    }

    private void addData(){
        employees = new ArrayList<>();
        for(int i=0;i<20;i++){
            employees.add(new Employee("Emp " + (i+1)));
        }
    }

    private void showToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}