package com.example.recyclerviewapp.card_rec_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.api.ApiClient;
import com.example.recyclerviewapp.api.TodoService;
import com.example.recyclerviewapp.models.TodoModel;
import com.example.recyclerviewapp.normal_rec_view.NormalRecViewActivity;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardRecViewActivity extends AppCompatActivity {
    private static final String TAG = "CardRecViewActivity";

    private RecyclerView cardRecView;
    private CardRecViewAdapter adapter;
    private CircularProgressIndicator loader;
    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_rec_view);

        cardRecView = findViewById(R.id.cardRecView);
        adapter = new CardRecViewAdapter(this);
        loader = findViewById(R.id.loader);
        todoService = ApiClient.getClient().create(TodoService.class);

        // SET ADAPTER AND LAYOUT MANAGER
        cardRecView.setAdapter(adapter);
        cardRecView.setLayoutManager(new LinearLayoutManager(this));

        // FETCH DATA
        loader.setVisibility(View.VISIBLE);
        fetchTodos();
    }

    private void fetchTodos(){
        Call<ArrayList<TodoModel>> call = todoService.getTodos();
        call.enqueue(new Callback<ArrayList<TodoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoModel>> call, Response<ArrayList<TodoModel>> response) {
                Log.d(TAG, "onResponse: TODOS_FETCHED");
                Toast.makeText(CardRecViewActivity.this, "Todos fetched", Toast.LENGTH_SHORT).show();
                adapter.setTodos(response.body());
                loader.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<TodoModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed" + t.getLocalizedMessage() );
                Toast.makeText(CardRecViewActivity.this, "Something went wrong, Try again!", Toast.LENGTH_SHORT).show();
                loader.setVisibility(View.GONE);
            }
        });
    }
}