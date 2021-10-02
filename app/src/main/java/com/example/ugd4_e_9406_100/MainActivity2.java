package com.example.ugd4_e_9406_100;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ugd4_e_9406_100.Adapter.TodoAdapter;
import com.example.ugd4_e_9406_100.Database.DatabaseTodo;
import com.example.ugd4_e_9406_100.Model.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private EditText edt_todo;
    private Button btnReset, btnAdd;
    private RecyclerView rv_todolist;

    private List<Todo> todoList;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_todo = findViewById(R.id.edt_todo);
        btnReset = findViewById(R.id.btnReset);
        btnAdd = findViewById(R.id.btnAdd);
        rv_todolist = findViewById(R.id.rv_todolist);

        rv_todolist.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_todo.getText().toString().isEmpty()){
                    addTodo();
                }else {
                    Toast.makeText(MainActivity2.this, "Belum diisi tuh", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_todo.setText("");
            }
        });
        getTodos();
        todoList = new ArrayList<>();
    }

    private void addTodo(){
        final String title = edt_todo.getText().toString();
        class AddTodo extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids){
                Todo todo = new Todo();
                todo.setTitle(title);

                DatabaseTodo.getInstance(getApplicationContext())
                        .getDatabase()
                        .todoDao()
                        .insertTodo(todo);
                return null;
            }
            @Override
            protected void onPostExecute(Void unused){
                super.onPostExecute(unused);
                Toast.makeText(MainActivity2.this,"Berhasil menambahkan data", Toast.LENGTH_SHORT).show();
                edt_todo.setText("");
                getTodos();
            }
        }
        AddTodo addTodo = new AddTodo();
        addTodo.execute();
    }

    private void getTodos(){
        class GetTodos extends AsyncTask<Void,Void,List<Todo>>{
            @Override
            protected List<Todo> doInBackground(Void... voids){
                List<Todo> todoList = DatabaseTodo.getInstance(getApplicationContext())
                        .getDatabase()
                        .todoDao()
                        .getAll();
                return todoList;
            }
            @Override
            protected void onPostExecute(List<Todo> todos){
                super.onPostExecute(todos);
                todoAdapter = new TodoAdapter(todos, MainActivity2.this);
                rv_todolist.setAdapter(todoAdapter);
            }
        }

        GetTodos getTodos = new GetTodos();
        getTodos.execute();
    }
}