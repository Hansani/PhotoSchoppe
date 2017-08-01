package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 01/08/2017.
 */

@ContentView(R.layout.activity_login)
public class LogInActivity extends RoboActivity {
    String user_name_sp, password_sp;
    @InjectView(R.id.txt_user_name_login)
    private EditText user_name;
    @InjectView(R.id.txt_password_login)
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void onClick(View view) {
        SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userdetail", 0);
        boolean isLogIn = preferences.getBoolean(getString(R.string.previously_started), Boolean.FALSE);
        if (!isLogIn) {
            user_name_sp = preferences.getString(getString(R.string.user_name_SP), "No User Name ");
            password_sp = preferences.getString(getString(R.string.password_SP), "No Password");
            if (user_name_sp.equals(user_name.getText().toString()) && password_sp.equals(password.getText().toString())) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(getString(R.string.is_login), Boolean.TRUE);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
