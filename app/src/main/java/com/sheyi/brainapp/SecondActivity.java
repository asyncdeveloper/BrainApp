package com.sheyi.brainapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        Intent intent = getIntent();
        String score = intent.getStringExtra("Score");
        String questions = intent.getStringExtra("questionsAnswered");
        resultTextView.setText("Your Score is "+ score + "/" + questions);
    }


    public void goBack(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
