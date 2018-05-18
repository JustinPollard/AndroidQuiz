package com.example.androidquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView myImageView = findViewById(R.id.imageViewTitle);
        myImageView.setImageResource(R.drawable.spacequiztitleimage);
        TextView scoresLabel = findViewById(R.id.scoresLabel);
        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        int totalScoreHard = settings.getInt("totalScoreHard", 0);
        scoresLabel.setText("Total Score (Easy Mode): " + totalScore + "\n Total Score (Hard Mode): " + totalScoreHard);
    }

    public void startQuiz(View view) {

        int quizMode = 0;
        Log.d("CREATION", "Testing...");
        switch (view.getId()) {
            case R.id.startEasy:
                quizMode = 1;
                break;
            case R.id.startHard:
                quizMode = 2;
                break;
        }

        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        intent.putExtra("QUIZ_MODE", quizMode);
        startActivity(intent);
    }

}
