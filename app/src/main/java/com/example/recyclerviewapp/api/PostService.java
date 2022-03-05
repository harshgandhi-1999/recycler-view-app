package com.example.recyclerviewapp.api;

import com.example.recyclerviewapp.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("/posts")
    Call<ArrayList<Post>> getPosts();
}
