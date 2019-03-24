package com.example.kearatingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kearatingapp.Model.ParseClass;

public class SelectActivity extends AppCompatActivity {

    Button goToRating;
    TextView loggedIn;
    Spinner teachers, semester, subject;
    ParseClass parseClass;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get the Intent that started this activity and extract the string
        Intent intentUsername = getIntent();
        username = intentUsername.getStringExtra("username");
        loggedIn = findViewById(R.id.menu_username);
        loggedIn.setText("You are logged in as: "+username);


        teachers = findViewById(R.id.spinnerteacher);
        semester = findViewById(R.id.spinnersemester);
        subject = findViewById(R.id.spinnersubject);
        goToRating = findViewById(R.id.button);

        final ArrayAdapter<CharSequence> first_adapter = ArrayAdapter.createFromResource(
                this, R.array.subjects1, R.layout.support_simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> second_adapter = ArrayAdapter.createFromResource(
                this, R.array.subjects2, R.layout.support_simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> third_adapter = ArrayAdapter.createFromResource(
                this, R.array.subjects3, R.layout.support_simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> fourth_adapter = ArrayAdapter.createFromResource(
                this, R.array.subjects4, R.layout.support_simple_spinner_dropdown_item);

        //Spinner for semestre, vil ændre på hvilke fag der er på semestret
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        subject.setAdapter(first_adapter);

                        break;
                    case 1:
                        subject.setAdapter(second_adapter);
                        break;
                    case 2:
                        subject.setAdapter(third_adapter);
                        break;
                    case 3:
                        subject.setAdapter(fourth_adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        goToRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    parseClass = new ParseClass(username, teachers.getSelectedItem().toString(), semester.getSelectedItem().toString(), subject.getSelectedItem().toString());
                    Intent intent = new Intent(SelectActivity.this, Subject.class);
                    intent.putExtra("subject", parseClass);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(SelectActivity.this,"Double check to see if all fields have been selected",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("teacher", teachers.getSelectedItemPosition());
        savedInstanceState.putInt("semester", semester.getSelectedItemPosition());
        savedInstanceState.putInt("subject", subject.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        teachers.setSelection(savedInstanceState.getInt("teacher"));
        semester.setSelection(savedInstanceState.getInt("semester"));
        subject.setSelection(savedInstanceState.getInt("subject"));
    }



}
