package com.example.recyclerviewapp.swipeable_item_rec_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.Post;

import java.util.ArrayList;

public class SwipeRecViewAdapter extends RecyclerView.Adapter<SwipeRecViewAdapter.MyViewHolder>{
    private ArrayList<Post> posts;
    private Context mContext;

    public SwipeRecViewAdapter(Context mContext) {
        this.mContext = mContext;
        this.posts = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setPostName(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void addPost(Post post){
        posts.add(0,post);
        notifyItemInserted(0);
    }

    public void deletePost(int index){
        posts.remove(index);
        notifyItemRemoved(index);
    }
    public Post getPost(int position){
        return posts.get(position);
    }

    // add the Post at position
    public void addPost(int position,Post post){
        posts.add(position,post);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtPostName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPostName = itemView.findViewById(R.id.txtPostName);
        }

        public void setPostName(final Post post){
            txtPostName.setText(post.getName());
        }
    }
}
