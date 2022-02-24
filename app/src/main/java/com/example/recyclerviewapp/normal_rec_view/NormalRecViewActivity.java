package com.example.recyclerviewapp.normal_rec_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.api.ApiClient;
import com.example.recyclerviewapp.api.TodoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NormalRecViewActivity extends AppCompatActivity {

    private static final String TAG = "NormalRecViewActivity";

    private RecyclerView normalRecView;
    private TodoRecViewAdapter adapter;
    private ArrayList<TodoModel> todos;
    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_rec_view);

        normalRecView = findViewById(R.id.normalRecView);
        adapter = new TodoRecViewAdapter(this);
        todos = new ArrayList<>();
        todoService = ApiClient.getClient().create(TodoService.class);

        fetchTodos();

        adapter.setTodos(todos);

        normalRecView.setAdapter(adapter);
        normalRecView.setLayoutManager(new LinearLayoutManager(this));
        normalRecView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }


    private void fetchTodos(){
        Call<ArrayList<TodoModel>> call = todoService.getTodos();
        call.enqueue(new Callback<ArrayList<TodoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoModel>> call, Response<ArrayList<TodoModel>> response) {
                Log.d(TAG, "onResponse: TODOS_FETCHED");
                Toast.makeText(NormalRecViewActivity.this, "Todos fetched", Toast.LENGTH_SHORT).show();
                adapter.setTodos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<TodoModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed" + t.getLocalizedMessage() );
                Toast.makeText(NormalRecViewActivity.this, "Something went wrong, Try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}