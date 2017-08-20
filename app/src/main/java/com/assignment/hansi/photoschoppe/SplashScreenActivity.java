package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

/**
 * Created by Hansi on 27/07/2017.
 */

@ContentView(R.layout.activity_splash_screen)
public class SplashScreenActivity extends RoboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userdetail", 0);
                    boolean previouslyStarted = preferences.getBoolean(getString(R.string.previously_started), Boolean.FALSE);
                    boolean is_login = preferences.getBoolean(getString(R.string.is_login), Boolean.FALSE);
                    String user_name = preferences.getString(getString(R.string.user_name_SP), "No Name");
                    if (!previouslyStarted) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(getString(R.string.previously_started), Boolean.TRUE);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                        startActivity(intent);
                    } else if(previouslyStarted && (user_name.equals("No Name")) ) {
                        Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                        startActivity(intent);
                    }else if (previouslyStarted && (user_name!= "No Name") && is_login){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else if(previouslyStarted && (user_name != "No Name") && !is_login){
                        finish();Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        thread.start();

    }



}
