package com.example.luckynumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

public class LuckyNumberActivity extends AppCompatActivity {
    private TextView luckyNumberText;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView userMessageText = findViewById(R.id.textView3);
        luckyNumberText = findViewById(R.id.textView4);
        Button shareBtn = findViewById(R.id.button2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userMessage = username +"'s lucky number:";
        userMessageText.setText(userMessage);

        luckyNumberText.setText(getLuckyNumber());
        shareBtn.setOnClickListener(view -> {
            shareLuckyNumber(username, luckyNumberText.getText().toString());
        });
    }

    private String getLuckyNumber(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(0, 37));
    }

    private void shareLuckyNumber(String username, String luckyNumber){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, username);
        intent.putExtra(Intent.EXTRA_TEXT, luckyNumber);
        startActivity(Intent.createChooser(intent, "Choose a platform"));
    }
}