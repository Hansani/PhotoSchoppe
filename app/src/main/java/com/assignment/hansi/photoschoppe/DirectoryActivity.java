package com.assignment.hansi.photoschoppe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.assignment.hansi.photoschoppe.adapter.PhotographerListAdapter;
import com.assignment.hansi.photoschoppe.db.connection.DBHandler;
import com.assignment.hansi.photoschoppe.model.Photographer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        dbHandler = new DBHandler(this);
        File database = getApplicationContext().getDatabasePath(DBHandler.DB_NAME);
        if (!database.exists()) {
            dbHandler.getReadableDatabase();
            if (copyDatabase(this)) {
                Toast.makeText(this, "copy database successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "copy database failed", Toast.LENGTH_SHORT).show();
            }
        }

        photographers = dbHandler.getAllPGs();
        listAdapter = new PhotographerListAdapter(this, sortPG(photographers));
        photographer_list.setAdapter(listAdapter);

    }

    //copy database
    public boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBHandler.DB_NAME);
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

    //sort list alphabetically order
    public List<Photographer> sortPG(List<Photographer> photographers){
        for (int i = 0; i < photographers.size(); i++) {
            Photographer temp = null;
            for (int j = 0; j < photographers.size(); j++) {
                if (photographers.get(i).getFirst_name().compareTo(photographers.get(j).getFirst_name()) < 0) {
                    temp = photographers.get(i);
                    photographers.set(i, photographers.get(j));
                    photographers.set(j, temp);
                }
            }
        }
        return photographers;
    }

}
