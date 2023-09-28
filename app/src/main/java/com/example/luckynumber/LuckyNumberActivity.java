package com.example.luckynumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

public class LuckyNumberActivity extends AppCompatActivity {
    private TextView luckyNumberText;
    private CheckBox option1, option2;
    private RadioGroup radioGroup;


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
        shareBtn.setOnClickListener(view -> shareLuckyNumber(username, luckyNumberText.getText().toString()));

        option1 = findViewById(R.id.checkBox);
        option2 = findViewById(R.id.checkBox2);
        radioGroup = findViewById(R.id.radioGroup);
        Button btn = findViewById(R.id.button3);

        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = findViewById(i);
            Toast.makeText(this, radioButton.getText() + " activated", Toast.LENGTH_SHORT).show();
        });

        btn.setOnClickListener(view -> {
            if (option1.isChecked()){
              Toast.makeText(getApplicationContext(), "Option 1 clicked", Toast.LENGTH_SHORT).show();
            }
            if ((option2.isChecked())){
                Toast.makeText(getApplicationContext(), "Option 2 clicked", Toast.LENGTH_SHORT).show();
            }
            if(!option1.isChecked() && !option2.isChecked()){
                Toast.makeText(this, "No option selected", Toast.LENGTH_LONG).show();
            }
        });

//        Resources resources = getResources();
//        String yep = resources.getQuantityString(R.plurals.numberOfSongs, 1);
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