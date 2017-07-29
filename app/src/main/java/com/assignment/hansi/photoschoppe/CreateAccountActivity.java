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

@ContentView(R.layout.activity_create_account)
public class CreateAccountActivity extends RoboActivity{

    @InjectView(R.id.txt_user_name) private EditText user_name;
    @InjectView(R.id.txt_email) private EditText email;
    @InjectView(R.id.txt_password) private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void createAccountMethod(View view){
        SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userDetail",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.user_name_SP),user_name.getText().toString());
        editor.putString(getString(R.string.email_SP),email.getText().toString());
        editor.putString(getString(R.string.password_SP),password.getText().toString());
        editor.commit();

        if (editor.commit()){
            Toast.makeText(getApplicationContext(), "create account successfully", Toast.LENGTH_SHORT).show();
        }

        Intent intent  = new Intent(this,  MainActivity.class);
        startActivity(intent);
    }

}
