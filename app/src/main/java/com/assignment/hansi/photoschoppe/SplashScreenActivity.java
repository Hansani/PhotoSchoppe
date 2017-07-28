package com.assignment.hansi.photoschoppe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.assignment.hansi.photoschoppe.db.connection.DBHandler;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
                    if (!previouslyStarted) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(getString(R.string.previously_started), Boolean.TRUE);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                        startActivity(intent);
                    } else {
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

    public static boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBHandler.getDbName());
            String outFileName = DBHandler.LOCATION + DBHandler.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] bytes = new byte[1024];
            int n = 0;
            while ((n = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, n);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
