package com.example.ugd4_e_9406_100;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ugd4_e_9406_100.Model.User;
import com.example.ugd4_e_9406_100.Preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private MaterialButton btnLogout;
    private User user;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferences = new UserPreferences(MainActivity.this);
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        user = userPreferences.getUserLogin();

        checkLogin();

        tvWelcome.setText("Selamat datang"+user.getUsername());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(MainActivity.this,"Baili baili", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });
    }

    private void checkLogin(){
        if(!userPreferences.checkLogin()){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }else {
            Toast.makeText(MainActivity.this,"Heyy kamu sudah login", Toast.LENGTH_SHORT).show();
        }
    }
}