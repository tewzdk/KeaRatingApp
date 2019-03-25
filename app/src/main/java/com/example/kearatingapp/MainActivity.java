package com.example.kearatingapp;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "Henrik";
    public static final String PASSWORD = "h";

    EditText username;
    EditText password;
    TextView message2;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        login = findViewById(R.id.loginbutton);
        message2 = findViewById(R.id.message);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString(),v);

            }
        });

    }

    //Uses intent to parse a String to other Activity
    public void validate(String userName, String password, View view)
    {
        if(userName.equals(USERNAME) && password.equals(PASSWORD)) {
            Intent intent = new Intent(this, SelectActivity.class);
            String message = username.getText().toString();

            try {
            intent.putExtra("username", message);
            startActivity(intent);

            } catch (Exception e ){
                message2.setText("its not working");
            }


        }
        else {
            Toast.makeText(MainActivity.this,"Wrong password or Username",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("username", username.getText().toString());
        savedInstanceState.putString("password", password.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        username.setText(String.valueOf(savedInstanceState.get("username")));
        password.setText(String.valueOf(savedInstanceState.get("password")));
    }

}



