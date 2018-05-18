package com.example.androidquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView resultsLabel = findViewById(R.id.resultsLabel);
        TextView scoresLabel = findViewById(R.id.scoresLabel);
        TextView perfectScoreLabel = findViewById(R.id.perfectScoreLabel);

        int score = getIntent().getIntExtra("rightAnswerCount", 0);
        int amountQuestions = 10;

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        int totalScoreHard = settings.getInt("totalScoreHard", 0);


/*
        if (quizMode == 2) {
            totalScoreHard += score;
        } else {
            totalScore += score;
        }
*/
        resultsLabel.setText(score + " / " + amountQuestions);
        scoresLabel.setText("Total Score (Easy Mode): " + totalScore + "\n Total Score (Hard Mode): " + totalScoreHard);

        if (score == amountQuestions) {
            perfectScoreLabel.setText("Perfect Score!");
        } else if (score > 0) {
            perfectScoreLabel.setText("Good Job!");
        } else {
            perfectScoreLabel.setText("Better Luck next time...");
        }
        int quizMode = getIntent().getIntExtra("QUIZ_MODE", 0);
        Log.v("MODE_OPTIONS", quizMode + "");
        //Update total score
        SharedPreferences.Editor editor = settings.edit();
        if (quizMode == 2) {
            editor.putInt("totalScore", totalScoreHard);
            Log.d("CREATION", "Testing... wrote to Hard Scores");
        } else {
            editor.putInt("totalScore", totalScore);
            Log.d("CREATION", "Testing... wrote to Easy Scores");
        }
        editor.apply();


    }

    public void returnTop(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}
