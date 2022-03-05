package com.example.recyclerviewapp.swipeable_item_rec_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.Toast;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.Post;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;


public class SwipeableRecViewActivity extends AppCompatActivity {

    private RecyclerView swipeableRecView;
    private SwipeRecViewAdapter adapter;
    private FloatingActionButton btnAddPost;
    private BottomSheetDialog bottomSheetDialog;
    private Button btnDone;
    private TextInputEditText editTxtPostName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeable_rec_view);
        initViews();

        swipeableRecView.setAdapter(adapter);
        swipeableRecView.setLayoutManager(new LinearLayoutManager(this));
        swipeableRecView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        btnAddPost.setOnClickListener(view -> onAddPost());
    }

    private void initViews(){
        //initializing recycler view
        swipeableRecView = findViewById(R.id.swipeableRecView);

        // initializing adapter
        adapter = new SwipeRecViewAdapter(this);

        //initializing bottom sheet
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.add_post_bottom_sheet_layout);

        bottomSheetDialog.setOnDismissListener(dialogInterface -> editTxtPostName.setText(""));


        // initializing fab button
        btnAddPost = findViewById(R.id.btnAddPost);
    }

    private void onAddPost(){
        showBottomSheetDialog();
    }

    private void showBottomSheetDialog(){
        btnDone = bottomSheetDialog.findViewById(R.id.btnDone);
        editTxtPostName = bottomSheetDialog.findViewById(R.id.editTextPostName);
        if (btnDone != null) {
            btnDone.setOnClickListener(view -> closeBottomSheetDialog());
        }

        String initialValue = editTxtPostName.getText().toString();
        if(initialValue.equals("")){
            btnDone.setEnabled(false);
        }else{
            btnDone.setEnabled(true);
        }

        editTxtPostName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnDone.setEnabled(!charSequence.toString().equals(""));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        
        btnDone.setOnClickListener(view -> {
            String postName = editTxtPostName.getText().toString();
            if(postName.equals("")){
                Toast.makeText(this, "Post name cannot be empty!", Toast.LENGTH_SHORT).show();
            }else{
                // add post to list and close the bottom sheet
                adapter.addPost(new Post(postName));
                Toast.makeText(this, "Post Added", Toast.LENGTH_SHORT).show();
                closeBottomSheetDialog();
            }
        });

        bottomSheetDialog.show();
    }

    private void closeBottomSheetDialog(){
        editTxtPostName.setText("");
        bottomSheetDialog.dismiss();
    }
}