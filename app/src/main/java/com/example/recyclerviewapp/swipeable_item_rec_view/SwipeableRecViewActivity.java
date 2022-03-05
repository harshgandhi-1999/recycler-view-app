package com.example.recyclerviewapp.swipeable_item_rec_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.Post;
import com.example.recyclerviewapp.models.TodoModel;
import com.example.recyclerviewapp.normal_rec_view.NormalRecViewActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(swipeableRecView);

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

    private Post deletedPost = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            if(direction==ItemTouchHelper.LEFT || direction==ItemTouchHelper.RIGHT){
                deleteItemFromListUtil(position);
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            // add swipe decorator for background
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(getResources().getColor(R.color.red))
                    .addActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeLeftLabel("DELETE")
                    .setSwipeLeftLabelColor(getResources().getColor(R.color.white))
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void deleteItemFromListUtil(int position){
        // store delete post
        deletedPost = adapter.getPost(position);
        adapter.deletePost(position);

        // then show snackbar for undoing the last item removed
        Snackbar.make(swipeableRecView,"Post deleted",Snackbar.LENGTH_LONG)
                .setAction("Undo", view -> {
                    adapter.addPost(position,deletedPost);
                })
                .show();
    }
}