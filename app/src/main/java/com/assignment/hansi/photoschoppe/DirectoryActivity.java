package com.assignment.hansi.photoschoppe;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.hansi.photoschoppe.adapter.PhotographerListAdapter;
import com.assignment.hansi.photoschoppe.db.connection.DBHandler;
import com.assignment.hansi.photoschoppe.model.Photographer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_directory)
public class DirectoryActivity extends RoboActivity {
    private PhotographerListAdapter listAdapter;
    private DBHandler dbHandler;
    private List<Photographer> photographers;

    @InjectView(R.id.photographer_list)
    private ListView photographer_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHandler handler = new DBHandler(this);

        File database = getApplicationContext().getDatabasePath(DBHandler.DB_NAME);
        if (!database.exists()) {
            handler.getReadableDatabase();
            if (SplashScreenActivity.copyDatabase(this)) {
                Toast.makeText(this, "copy database successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "copy database failed", Toast.LENGTH_SHORT).show();
            }
        }

        photographers = handler.getAllPGs();
        listAdapter = new PhotographerListAdapter(this, photographers);

        photographer_list.setAdapter(listAdapter);

    }



}
