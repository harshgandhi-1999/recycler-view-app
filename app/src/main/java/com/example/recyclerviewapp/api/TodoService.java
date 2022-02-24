package com.example.recyclerviewapp.api;

import com.example.recyclerviewapp.normal_rec_view.TodoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TodoService {
    @GET("/todos")
    Call<ArrayList<TodoModel>> getTodos();

    @GET("/todos/{id}")
    Call<TodoModel> getTodoById(@Path("id") int id);

    @GET("/todos")
    Call<ArrayList<TodoModel>> getTodosUsingQuery(@Query("userId") int userId, @Query("completed") boolean completed);

    @POST("/todos")
    Call<TodoModel> postTodo(@Body TodoModel todo);
}
