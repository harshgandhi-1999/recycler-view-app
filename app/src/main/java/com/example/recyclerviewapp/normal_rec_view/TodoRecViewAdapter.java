package com.example.recyclerviewapp.normal_rec_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewapp.R;

import java.util.ArrayList;

public class TodoRecViewAdapter extends RecyclerView.Adapter<TodoRecViewAdapter.MyViewHolder>{
    private Context myContext;
    private ArrayList<TodoModel> todos = new ArrayList<>();

    public TodoRecViewAdapter(Context myContext) {
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.todo_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TodoModel todo = todos.get(position);
        holder.setDetails(todo);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setTodos(ArrayList<TodoModel> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    // inner class
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtViewId;
        private TextView txtViewUserId;
        private TextView txtViewTitle;
        private TextView txtViewCompletedStatus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewId = itemView.findViewById(R.id.txtViewId);
            txtViewUserId = itemView.findViewById(R.id.txtViewUserId);
            txtViewTitle = itemView.findViewById(R.id.txtViewTitle);
            txtViewCompletedStatus = itemView.findViewById(R.id.txtViewCompletedStatus);
        }

        private void setDetails(TodoModel todo){
            txtViewId.setText(String.valueOf(todo.getId()));
            txtViewUserId.setText(String.valueOf(todo.getUserId()));
            txtViewTitle.setText(todo.getTitle());
            txtViewCompletedStatus.setText(todo.isCompleted() ? "true" : "false");
        }
    }
}
