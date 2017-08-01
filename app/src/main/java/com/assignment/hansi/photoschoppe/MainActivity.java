package com.assignment.hansi.photoschoppe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.hansi.photoschoppe.adapter.PhotographerListAdapter;
import com.assignment.hansi.photoschoppe.db.connection.DBHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.txt_view_hi)
    private TextView hi_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userdetail", 0);
        String user_name = preferences.getString(getString(R.string.user_name_SP), "not available");
        String original = getString(R.string.hi_name);
        String new_hi_string = String.format(original, user_name);
        hi_txt.setText(new_hi_string);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void pressedPortfolio(View view) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        startActivity(intent);
    }

    public void pressedDirectory(View view) {
        Intent intent = new Intent(this, DirectoryActivity.class);
        startActivity(intent);
    }


    public void pressedMore(View view) {
        Intent intent = new Intent(this, MoreActivity.class);
        startActivity(intent);
    }

    public void logoutPerformed(View view) {
        SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userdetail", 0);
        boolean is_login = preferences.getBoolean(getString(R.string.is_login), Boolean.FALSE);
        if (is_login) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(getString(R.string.previously_started), Boolean.FALSE);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
