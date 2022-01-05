package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartQuizz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quizz);
    }

    public void startGame(View view) {

        Intent intent = new Intent(StartQuizz.this, StartGame.class);
        startActivity(intent);

        finish();
    }
}