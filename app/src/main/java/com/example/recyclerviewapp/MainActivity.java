package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.recyclerviewapp.normal_rec_view.NormalRecViewActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnNormalRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnNormalRecView.setOnClickListener(view -> openNormalRecView());
    }

    private void initViews(){
        btnNormalRecView = findViewById(R.id.btnNormalRecView);
    }

    private void openNormalRecView(){
        Intent intent = new Intent(this, NormalRecViewActivity.class);
        startActivity(intent);
    }

}