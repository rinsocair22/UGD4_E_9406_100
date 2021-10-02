package com.example.ugd4_e_9406_100.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ugd4_e_9406_100.Dao.TodoDao;
import com.example.ugd4_e_9406_100.Model.Todo;

@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();

}
