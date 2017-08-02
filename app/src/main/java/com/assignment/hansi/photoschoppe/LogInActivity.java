package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.MainThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 01/08/2017.
 */

@ContentView(R.layout.activity_login)
public class LogInActivity extends ValidatorActivity {
    int login_try = 0;

    String user_name_sp, password_sp;


    @NotEmpty(message = "Enter User Name")
    @InjectView(R.id.Login_txt_user_name)
    private EditText user_name;

    @NotEmpty(message = "Enter Password")
    @Password(min=8,message = "Password should be at least 8 digit")
    @InjectView(R.id.Login_txt_password)
    private EditText password;

    @InjectView(R.id.exception)
    private TextView error;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void onClick(View view) {
        super.onClick(view);
        SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userdetail", 0);
        boolean isLogIn = preferences.getBoolean(getString(R.string.is_login), Boolean.FALSE);
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
            else {
                user_name.setText(null);
                user_name.setHint(R.string.txt_user_name);
                password.setText(null);
                password.setHint(R.string.txt_password);
                error.setText(getString(R.string.log_in_error));
                login_try+=1;
                if (login_try==3){
                    error.setText(getString(R.string.log_in_restricted));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }

        }
    }
}
