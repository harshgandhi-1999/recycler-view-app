package com.example.recyclerviewapp.normal_rec_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerviewapp.R;

public class NormalRecViewActivity extends AppCompatActivity {

    private RecyclerView normalRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_rec_view);

        normalRecView = findViewById(R.id.normalRecView);
    }
}