package com.example.ugd4_e_9406_100.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseTodo {
    private Context context;
    private static DatabaseTodo databaseTodo;

    private AppDatabase database;

    public DatabaseTodo(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class,"todo").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseTodo getInstance(Context context){
        if (databaseTodo == null){
            databaseTodo = new DatabaseTodo(context);
        }
        return databaseTodo;
    }

    public AppDatabase getDatabase(){return database;}
}
