package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_login)
public class LogInActivity extends RoboActivity {

    String user_name_sp;
    String password_sp;
    int attempts = 0;

    @InjectView(R.id.txt_user_name_login) private EditText user_name;
    @InjectView(R.id.txt_password_login) private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userDetail",0);
        user_name_sp = preferences.getString(getString(R.string.user_name_SP), "not applicable");
        password_sp = preferences.getString(getString(R.string.password_SP), "");
    }

    public void onClick(View view){
        while (attempts < 4){
            if (user_name.getText().toString().equals(user_name_sp) && password.getText().toString().equals(password_sp)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "you have login successfully", Toast.LENGTH_SHORT).show();
            }else {
                attempts+=1;
                Toast.makeText(getApplicationContext(),"you have entered wrong password or user_name", Toast.LENGTH_SHORT).show();
            }
        }
        Toast.makeText(getApplicationContext(),"no more attempts are allowed", Toast.LENGTH_SHORT).show();

    }
}
