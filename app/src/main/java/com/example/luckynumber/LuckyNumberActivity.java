package com.example.luckynumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

public class LuckyNumberActivity extends AppCompatActivity {
    private TextView luckyNumberText;
    private CheckBox option1, option2;
    private Spinner spinner;


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
        RadioGroup radioGroup1 = findViewById(R.id.radioGroup);
        Button btn = findViewById(R.id.button3);

        //radio
        radioGroup1.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = findViewById(i);
            Toast.makeText(this, radioButton.getText() + " activated", Toast.LENGTH_SHORT).show();
        });
        //dropdown
        Resources resources = getResources();
        spinner = findViewById(R.id.spinner);
        String[] colors = resources.getStringArray(R.array.color);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, colors);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ;
        spinner.setAdapter(adapter);

        spinner.setSelection(ThreadLocalRandom.current().nextInt(0, 3));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(LuckyNumberActivity.this, "color "+ colors[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //checkbox
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