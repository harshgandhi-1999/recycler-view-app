package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.recyclerviewapp.card_rec_view.CardRecViewActivity;
import com.example.recyclerviewapp.multi_selection_rec_view.MultiSelectionActivity;
import com.example.recyclerviewapp.normal_rec_view.NormalRecViewActivity;
import com.example.recyclerviewapp.single_selection_rec_view.SingleSelectionRecViewActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnNormalRecView,btnCardRecView,btnSingleSelection,btnMultipleSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnNormalRecView.setOnClickListener(view -> openNormalRecView());
        btnCardRecView.setOnClickListener(view -> openCardRecView());
        btnSingleSelection.setOnClickListener(view -> openSingleSelectionRecView());
        btnMultipleSelection.setOnClickListener(view -> openMultipleSelectionRecView());
    }

    private void initViews(){
        btnNormalRecView = findViewById(R.id.btnNormalRecView);
        btnCardRecView = findViewById(R.id.btnCardRecView);
        btnSingleSelection = findViewById(R.id.btnSingleSelection);
        btnMultipleSelection = findViewById(R.id.btnMultipleSelection);
    }

    private void openNormalRecView(){
        Intent intent = new Intent(this, NormalRecViewActivity.class);
        startActivity(intent);
    }
    private void openCardRecView(){
        Intent intent = new Intent(this, CardRecViewActivity.class);
        startActivity(intent);
    }

    private void openSingleSelectionRecView(){
        Intent intent = new Intent(this, SingleSelectionRecViewActivity.class);
        startActivity(intent);
    }

    private void openMultipleSelectionRecView(){
        Intent intent = new Intent(this, MultiSelectionActivity.class);
        startActivity(intent);
    }

}