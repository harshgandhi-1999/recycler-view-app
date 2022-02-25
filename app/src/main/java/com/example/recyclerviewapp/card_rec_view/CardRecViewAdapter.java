package com.example.recyclerviewapp.card_rec_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.TodoModel;

import java.util.ArrayList;

public class CardRecViewAdapter extends RecyclerView.Adapter<CardRecViewAdapter.MyViewHolder>{
    private Context myContext;
    private ArrayList<TodoModel> todos = new ArrayList<>();

    public CardRecViewAdapter(Context myContext) {
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.todo_item_card_layout,parent,false);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtViewIdCard;
        private TextView txtViewUserIdCard;
        private TextView txtViewTitleCard;
        private TextView txtViewCompletedStatusCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewIdCard = itemView.findViewById(R.id.txtViewIdCard);
            txtViewUserIdCard = itemView.findViewById(R.id.txtViewUserIdCard);
            txtViewTitleCard = itemView.findViewById(R.id.txtViewTitleCard);
            txtViewCompletedStatusCard = itemView.findViewById(R.id.txtViewCompletedStatusCard);
        }
        private void setDetails(TodoModel todo){
            txtViewIdCard.setText(String.valueOf(todo.getId()));
            txtViewUserIdCard.setText(String.valueOf(todo.getUserId()));
            txtViewTitleCard.setText(todo.getTitle());
            txtViewCompletedStatusCard.setText(todo.isCompleted() ? "true" : "false");

            txtViewCompletedStatusCard.setTextColor(todo.isCompleted() ? itemView.getResources().getColor(R.color.green) : itemView.getResources().getColor(R.color.red));
        }
    }
}
