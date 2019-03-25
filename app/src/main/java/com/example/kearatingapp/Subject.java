package com.example.kearatingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.kearatingapp.Model.ParseClass;
import com.example.kearatingapp.Service.SendEmail;

public class Subject extends AppCompatActivity {

    TextView txt_subrel,txt_perf, txt_prep, txt_feedback,txt_example,txt_opportunity;
    TextView show_subrel, show_perf,show_prep,show_feedback,show_example,show_opportunity;
    SeekBar sb_subrel, sb_perf, sb_prep, sb_feedback, sb_examples, sb_opportunity;
    TextView success_title, success_body, title;
    Button createRating;
    SendEmail sendEmail;
    Intent intent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        sendEmail = new SendEmail();
        intent = getIntent();
        final ParseClass parseClass = intent.getParcelableExtra("subject");

        title = findViewById(R.id.subjectTitle);
        title.setText("Rate " + parseClass.getTeacher() + " In " + parseClass.getSubject()+ " on the following parameters:");
        createRating = findViewById(R.id.btn_createRating);


        //SEEKBARS
        sb_subrel = findViewById(R.id.sb_relevance);
        sb_perf = findViewById(R.id.sb_performance);
        sb_prep = findViewById(R.id.sb_prep);
        sb_feedback = findViewById(R.id.sb_feedback);
        sb_examples = findViewById(R.id.sb_examples);
        sb_opportunity = findViewById(R.id.sb_jobopp);

        //TEXTVIEW TITLES
        txt_subrel = findViewById(R.id.txt_relevance);
        txt_perf = findViewById(R.id.txt_performance);
        txt_prep = findViewById(R.id.txt_prep);
        txt_feedback = findViewById(R.id.txt_feedback);
        txt_example = findViewById(R.id.txt_examples);
        txt_opportunity = findViewById(R.id.txt_jobopportunity);

        //TEXTVIEW USED TO SHOW SEEKBAR VALUE
        show_subrel = findViewById(R.id.show_subrel);
        show_perf = findViewById(R.id.show_perf);
        show_prep = findViewById(R.id.show_prep);
        show_feedback = findViewById(R.id.show_feedback);
        show_example = findViewById(R.id.show_examples);
        show_opportunity = findViewById(R.id.show_jobop);

        //LISTENERS TO FIND VALUE OF SEEKBAR
        sb_subrel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                show_subrel.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_perf.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                show_perf.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_prep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                show_prep.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_feedback.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                show_feedback.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_examples.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                show_example.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_opportunity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                show_opportunity.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        createRating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                submit(parseClass);
           }
        });

    }

    public void submit(ParseClass parseClass) {
        String subjectTitle = parseClass.getUsername() + " has rated you for the class "+ parseClass.getSubject();

        int average = ((sb_perf.getProgress()+sb_prep.getProgress()+sb_subrel.getProgress()+sb_feedback.getProgress()+sb_examples.getProgress()+sb_opportunity.getProgress())/6);

        String messageBody =
                "Hello " + parseClass.getTeacher() + ",\n" + parseClass.getUsername() + " on " +
                        parseClass.getSemester() + " has rated you in the subject: " + parseClass.getSubject() + ".\n\n" +
                        txt_subrel.getText().toString() + ": " + sb_perf.getProgress() + "\n" +
                        txt_perf.getText().toString() + ": " + sb_perf.getProgress() + "\n" +
                        txt_prep.getText().toString() + ": " + sb_prep.getProgress() + "\n" +
                        txt_feedback.getText().toString() + ": " + sb_feedback.getProgress() + "\n" +
                        txt_example.getText().toString() + ": " + sb_examples.getProgress() + "\n" +
                        txt_opportunity.getText().toString() + ": " + sb_opportunity.getProgress() + "\n\n" +
                        "The average rating is: " + average;

        sendEmail.send("Tewzdk@gmail.com",subjectTitle,messageBody);

        success(parseClass);

    }

    public void success(ParseClass parseClass) {
        setContentView(R.layout.activity_success);
        success_title = findViewById(R.id.success_username);
        success_title.setText("Thank you "+parseClass.getUsername() + "!");
        success_body = findViewById(R.id.success_textfield);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("subrel", sb_subrel.getProgress());
        savedInstanceState.putInt("perf", sb_perf.getProgress());
        savedInstanceState.putInt("prep", sb_prep.getProgress());
        savedInstanceState.putInt("feedback", sb_feedback.getProgress());
        savedInstanceState.putInt("ex", sb_examples.getProgress());
        savedInstanceState.putInt("opp", sb_opportunity.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        sb_subrel.setProgress(savedInstanceState.getInt("subrel"));
        sb_perf.setProgress(savedInstanceState.getInt("perf"));
        sb_prep.setProgress(savedInstanceState.getInt("prep"));
        sb_feedback.setProgress(savedInstanceState.getInt("feedback"));
        sb_examples.setProgress(savedInstanceState.getInt("ex"));
        sb_opportunity.setProgress(savedInstanceState.getInt("opp"));
    }




}
