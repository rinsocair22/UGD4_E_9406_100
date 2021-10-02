package com.example.ugd4_e_9406_100.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ugd4_e_9406_100.Model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo")
        List<Todo> getAll();
    @Insert
    Void insertTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

}
