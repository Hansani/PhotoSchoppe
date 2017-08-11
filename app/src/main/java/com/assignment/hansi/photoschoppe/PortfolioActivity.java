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
    private static final String url = "https://api.flickr.com/services/feeds/photos_public.gne?id=" +
            "60269086@N05&format=json&nojsoncallback=1";
    private List<Image> imageList = new ArrayList<>();
    private ImageListAdapter imageListAdapter;
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
        JsonObjectRequest job = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hideDialog();
                //parsing JSON
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Image image = new Image();
                        image.setTitle(jsonObject.getString("title"));
                        image.setLink(jsonObject.getString("link"));
                        JSONObject object = jsonObject.getJSONObject("media");
                        image.setMedia(object.getString("m"));
                        imageList.add(image);
                    }
                    imageListAdapter = new ImageListAdapter(imageList,getContext());
                    photos_list.setAdapter(imageListAdapter);
                    imageListAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance(this.getApplicationContext()).addToRequestQueue(job);
//        AppController.getInstance(this.getApplicationContext()).addToRequestQueue(AppController.
//                getInstance(this).loadImage(photos_list));

    }

    public void hideDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public Context getContext(){
        return this.getApplicationContext();
    }

}







