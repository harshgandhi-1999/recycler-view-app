package com.example.recyclerviewapp.multi_selection_rec_view;

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

public class MultiSelectionActivity extends AppCompatActivity {

    private MultiAdapter adapter;
    private ArrayList<Employee> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_selection);

        RecyclerView recyclerView = findViewById(R.id.multipleSelectionRecView);
        adapter = new MultiAdapter(this);
        Button btnGetMultiSelectedItems = findViewById(R.id.btnGetMultiSelectedItems);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        addData();

        adapter.setEmployees(employees);

        btnGetMultiSelectedItems.setOnClickListener(view -> {
            if(adapter.getSelectedEmployees().isEmpty()){
                showToast("No selection");
            }else{
                StringBuilder builder = new StringBuilder();
                for(int i=0;i<adapter.getSelectedEmployees().size();i++){
                    builder.append(adapter.getSelectedEmployees().get(i).getName());
                    builder.append("\n");
                }

                showToast(builder.toString().trim());
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