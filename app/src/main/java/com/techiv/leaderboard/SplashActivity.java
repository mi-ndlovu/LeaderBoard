package com.techiv.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.techiv.leaderboard.dto.DataManager;
import com.techiv.leaderboard.dto.Student;
import com.techiv.leaderboard.dto.StudentIQ;
import com.techiv.leaderboard.utils.NetworkUtils;

import java.util.List;

import static com.techiv.leaderboard.utils.Constants.LEARNING_LEADERS;
import static com.techiv.leaderboard.utils.Constants.SKILL_IQ_LEADERS;


public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void loadData() {
        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... urls) {
                List<Student> students = NetworkUtils.fetchLearningHoursData(urls[0]);
                List<StudentIQ> studentIQs = NetworkUtils.fetchSkillIQData(urls[1]);
                DataManager.setLearningHours(students);
                DataManager.setSkillsIQ(studentIQs);
                return null;
            }
        };
        task.execute(LEARNING_LEADERS, SKILL_IQ_LEADERS);
    }
}