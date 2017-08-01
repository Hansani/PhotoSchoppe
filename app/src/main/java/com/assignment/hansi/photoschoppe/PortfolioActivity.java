package com.assignment.hansi.photoschoppe;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.assignment.hansi.photoschoppe.adapter.ImageListAdapter;
import com.assignment.hansi.photoschoppe.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_portfolio)
public class PortfolioActivity extends RoboActivity {
    private Context context;
    private ProgressDialog progressDialog;

    @InjectView(R.id.photos_list_view)
    private ListView photos_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_portfolio);
        this.context=this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Load Images...");
        progressDialog.show();

        //Create Volley Request
        AppController.getInstance(context.getApplicationContext()).addToRequestQueue(AppController.getInstance(this).loadImage(photos_list));

    }



}


