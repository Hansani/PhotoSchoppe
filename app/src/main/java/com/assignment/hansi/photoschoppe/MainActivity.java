package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.txt_view_hi) private TextView hi_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userDetail",0);
        String user_name = preferences.getString("user_name", "not available");
        String original = getString(R.string.hi_name);
        String new_hi_string = String.format(original, user_name);
        hi_txt.setText(new_hi_string);
    }

    public void pressedPortfolio(View view){
        Intent intent = new Intent(this, PortfolioActivity.class);
        startActivity(intent);
    }

    public void pressedDirectory(View view){
        Intent intent = new Intent(this, DirectoryActivity.class);
        startActivity(intent);
    }

    public void pressedMore(View view){
        Intent intent = new Intent(this, MoreActivity.class);
        startActivity(intent);
    }


}
