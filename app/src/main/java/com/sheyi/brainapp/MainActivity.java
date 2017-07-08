package com.sheyi.brainapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int score= 0;
    Button startButton;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int numberOfQuestions=0;
    TextView resultTextView;
    TextView sumTextView;
    TextView scoreTextView;
    TextView timerTextView;
    RelativeLayout gameRelativeLayout;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        startButton = (Button)findViewById(R.id.startButton);
        button  = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        sumTextView    = (TextView)findViewById(R.id.sumTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView  = (TextView)findViewById(R.id.scoreTextView);
        timerTextView  = (TextView)findViewById(R.id.timerTextView);
    }

    public void start(View view){
        new CountDownTimer(3100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                String currentSecond =String.valueOf(millisUntilFinished/1000);
                startButton.setText(currentSecond);
                startButton.setClickable(false);
            }
            @Override
            public void onFinish() {
                startButton.setVisibility(View.INVISIBLE);
                gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
                playAgain();
            }
        }.start();
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");

        }else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void generateQuestion() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for (int i=0; i<4 ;i++) {
            if(i == locationOfCorrectAnswer) {
                answers.add(a+b);
            }else{
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(){
        score = 0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        generateQuestion();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("Score",String.valueOf(score));
                intent.putExtra("questionsAnswered",String.valueOf(numberOfQuestions));
                startActivity(intent);
            }
        }.start();
    }



}

