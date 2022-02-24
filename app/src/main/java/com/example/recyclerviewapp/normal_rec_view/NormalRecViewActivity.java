package com.example.recyclerviewapp.normal_rec_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerviewapp.R;

import java.util.ArrayList;

public class NormalRecViewActivity extends AppCompatActivity {

    private RecyclerView normalRecView;
    private TodoRecViewAdapter adapter;
    private ArrayList<TodoModel> todos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_rec_view);

        normalRecView = findViewById(R.id.normalRecView);
        adapter = new TodoRecViewAdapter(this);
        todos = new ArrayList<>();

        createList();

        adapter.setTodos(todos);

        normalRecView.setAdapter(adapter);
        normalRecView.setLayoutManager(new LinearLayoutManager(this));
        normalRecView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }


    private void createList(){
        TodoModel todo = new TodoModel(1,1,"abc",true);
        todos.add(todo);
    }
}