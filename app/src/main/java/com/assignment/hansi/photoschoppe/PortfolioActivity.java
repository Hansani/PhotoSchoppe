package com.assignment.hansi.photoschoppe;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_portfolio)
public class PortfolioActivity extends RoboActivity {
    private ProgressDialog progressDialog;

    @InjectView(R.id.photos_list_view)
    private ListView photos_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Load Images...");
        progressDialog.show();

        //Create Volley Request
        AppController.getInstance(this.getApplicationContext()).addToRequestQueue(AppController.
                getInstance(this).loadImage(photos_list));
    }

}







