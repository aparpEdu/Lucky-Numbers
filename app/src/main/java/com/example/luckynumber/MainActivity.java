package com.example.luckynumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usernameInput;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        usernameInput = findViewById(R.id.textView2);
        Button goBtn = findViewById(R.id.button);

        goBtn.setOnClickListener(view -> {
            String username = usernameInput.getText().toString();
            validateInput(username);
        });
    }


    private void validateInput(String username){
        if(username == null || username.isEmpty() || username.isBlank()){
            Toast.makeText(getApplicationContext(),"Please input valid username",
                    Toast.LENGTH_LONG).show();
        } else{
            Intent intent = new Intent(this, LuckyNumberActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
}