package com.assignment.hansi.photoschoppe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.assignment.hansi.photoschoppe.adapter.ImageListAdapter;
import com.assignment.hansi.photoschoppe.model.Image;
import com.assignment.hansi.photoschoppe.network.handling.VolleyImageDownloadHelper;
import com.assignment.hansi.photoschoppe.network.handling.VolleyJsonDownloadHelper;

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


    @InjectView(R.id.photos_list_view)
    private ListView photos_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<Image> images = new ArrayList<>();
        ImageListAdapter adapter =  new ImageListAdapter(this,images);
        final Image image = new Image();
        JsonObjectRequest request = new JsonObjectRequest("https://api.flickr.com/services/feeds/photos_public.gne?id=60269086@N05&format=json",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray imageArray = response.getJSONArray("items");
                            //for (int i = 0; i < contactsArray.length(); i++) {
                            JSONObject job = imageArray.getJSONObject(0);

                            image.setTitle(job.getString("title"));
                            image.setLink(job.getString("link"));
                            image.setMedia(job.getString("media"));
                            image.setDate_taken(job.getString("date_taken"));
                            image.setDescription(job.getString("description"));
                            image.setPublished(job.getString("published"));
                            image.setAuthor(job.getString("author"));
                            image.setAuthor_id(job.getString("author_id"));
                            image.setTags(job.getString("tags"));

                            images.add(image);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // With the request created, simply add it to our Application's RequestQueue
        VolleyJsonDownloadHelper.getInstance(this.getApplicationContext()).getRequestQueue().add(request);

        //load images to the image adapter
        photos_list.setAdapter(adapter);

}

    private TextView mTextView1, mTextView2, mTextView3;

}


