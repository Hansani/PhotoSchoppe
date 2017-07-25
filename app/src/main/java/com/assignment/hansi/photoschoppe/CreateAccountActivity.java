package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        editor.putString("user_name",user_name.getText().toString());
        editor.putString("email",email.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.commit();

        if (editor.commit()){
            //send message to the user telling that he is now log in
        }

        Intent intent  = new Intent(this,  MainActivity.class);
        startActivity(intent);
    }

}
