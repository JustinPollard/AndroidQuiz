package com.example.androidquiz;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void authorise(View view) {
        Intent intent = new Intent(this, Authenticate.class);
        startActivityForResult(intent, AUTHENTICATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == AUTHENTICATE && resultCode == RESULT_OK) {
            Background.run(new Runnable() {
                @Override
                public void run() {
                    String token = data.getStringExtra("access token");
                    String secret = data.getStringExtra("access token secret");
                    AccessToken accessToken = new AccessToken(token, secret);
                    twitter.setOAuthAccessToken(accessToken);

                    DownloadManager.Query query = new DownloadManager.Query("@twitterapi");
                    QueryResult result;
                    try {
                        twitter.updateStatus("everything is fine!");
                        result = twitter.search(query);
                    } catch (final Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(e.toString());
                            }
                        });
                        return;
                    }

                    // convert tweets into text
                    final StringBuilder builder = new StringBuilder();
                    for (Status status : result.getTweets()) {
                        builder.append(status.getUser().getScreenName())
                                .append(" said ")
                                .append(status.getText())
                                .append("\n");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(builder.toString().trim());
                        }
                    });
                }
            });
        }

}
