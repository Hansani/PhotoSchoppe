package com.assignment.hansi.photoschoppe;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.assignment.hansi.photoschoppe.adapter.ImageListAdapter;
import com.assignment.hansi.photoschoppe.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hansi on 30/07/2017.
 */

public class AppController {
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue requestQueue;
    private ImageLoader imLoader;
    private static AppController mInstance;
    private Context context;
    private List<Image> imageList = new ArrayList<>();
    private ImageListAdapter imageListAdapter;
    private static final String url = "https://api.flickr.com/services/feeds/photos_public.gne?id=60269086@N05&format=json&nojsoncallback=1";
    private ProgressDialog progressDialog;


    private AppController(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
        getImLoader();
    }


    public static synchronized AppController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppController(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
            requestQueue.start();
        }
        return requestQueue;
    }

    public ImageLoader getImLoader() {
        getRequestQueue();
        if (imLoader == null) {
            imLoader = new ImageLoader(this.requestQueue, new BitmapCache());
        }
        return this.imLoader;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void canclePendingRequest(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public JsonObjectRequest loadImage(final ListView photos_list) {

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
                        JSONObject object = jsonObject.getJSONObject("media");
                        image.setMedia(object.getString("media"));
                        image.setMedia(object.getString("link"));
                        imageList.add(image);
                    }
                    imageListAdapter = new ImageListAdapter(this, imageList, context.getApplicationContext());
                    imageListAdapter.notifyDataSetChanged();
                    photos_list.setAdapter(imageListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return job;

    }

    public void hideDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
