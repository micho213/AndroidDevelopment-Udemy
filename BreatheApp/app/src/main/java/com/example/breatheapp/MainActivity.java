package com.example.breatheapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breatheapp.util.Prefs;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import org.w3c.dom.Text;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView breathsTxt, timeTxt, sessonTxt, guideTxt, currentTimer;

    private Button startButton;

    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.lotusImage);
        breathsTxt = findViewById(R.id.breathsTakenTxt);
        timeTxt = findViewById(R.id.lastBreathTxt);
        sessonTxt = findViewById(R.id.todayMinutesTxt);
        startButton = findViewById(R.id.startButton);
        guideTxt = findViewById(R.id.breathe);
        currentTimer = findViewById(R.id.currentTime);

        prefs = new Prefs(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });

        startIntroAnimation();

        updateTextViews();

    }

    private void updateTextViews(){
        sessonTxt.setText(MessageFormat.format("{0} minutes today", prefs.getSessions()));
        breathsTxt.setText(MessageFormat.format("{0} Breaths" , prefs.getBreaths()));
        timeTxt.setText(MessageFormat.format(" last session: {0}", prefs.getDate()));
    }

    private void startIntroAnimation () {
        ViewAnimator.animate(guideTxt)
                .scale(0,1)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        guideTxt.setText("Breathe");
                    }
                })
                .duration(2500)
                .start();
    }


    private void startTimer(int noOfMinutes) {
        CountDownTimer  countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d",  TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                currentTimer.setText(hms);//set text
            }
            public void onFinish() {
                currentTimer.setText("Done"); //On finish change timer text

            }
        }.start();

    }

    private void startAnimation(){

        String check = (String) startButton.getText();
        if (check.equals( "START") ) {

            startTimer(60000);
            startButton.setText("Stop");

            ViewAnimator.animate(image)
                    .alpha(0, 1)
                    .onStart(new AnimationListener.Start() {
                        @Override
                        public void onStart() {
                            guideTxt.setText("Inhale... Exhale");
                        }
                    })
                    .decelerate()
                    .duration(1)
                    .thenAnimate(image)
                    .scale(0.02f, 1.5f, 0.02f)
                    .rotation(360)
                    .repeatCount(100)
                    .accelerate()
                    .duration(5500)
                    .onStop(new AnimationListener.Stop() {
                        @Override
                        public void onStop() {
                            guideTxt.setText("Good Job!");
                            image.setScaleX(1.0f);
                            image.setScaleY(1.0f);

                            prefs.setSessons(prefs.getSessions() + 1);
                            prefs.setBreaths(prefs.getBreaths() + 1);
                            prefs.setDate(System.currentTimeMillis());

                            updateTextViews();

                            new CountDownTimer(1, 1000) {

                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            }.start();
                        }
                    })
                    .start();

        } else {
            startButton.setText("START");
            updateTextViews();

            new CountDownTimer(1, 1000) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }.start();
        }

    }

}
