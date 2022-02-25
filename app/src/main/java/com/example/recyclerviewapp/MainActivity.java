package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.recyclerviewapp.card_rec_view.CardRecViewActivity;
import com.example.recyclerviewapp.normal_rec_view.NormalRecViewActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnNormalRecView,btnCardRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnNormalRecView.setOnClickListener(view -> openNormalRecView());
        btnCardRecView.setOnClickListener(view -> openCardRecView());
    }

    private void initViews(){
        btnNormalRecView = findViewById(R.id.btnNormalRecView);
        btnCardRecView = findViewById(R.id.btnCardRecView);
    }

    private void openNormalRecView(){
        Intent intent = new Intent(this, NormalRecViewActivity.class);
        startActivity(intent);
    }
    private void openCardRecView(){
        Intent intent = new Intent(this, CardRecViewActivity.class);
        startActivity(intent);
    }

}