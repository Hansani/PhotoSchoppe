package com.assignment.hansi.photoschoppe;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.assignment.hansi.photoschoppe.adapter.ImageListAdapter;
import com.assignment.hansi.photoschoppe.model.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hansi on 30/07/2017.
 */

public class AppController {
    private static final String url = "https://api.flickr.com/services/feeds/photos_public.gne?id=60269086@N05&format=json&nojsoncallback=1";
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue requestQueue;
    private ImageLoader imLoader;
    private static AppController mInstance;
    private Context context;
    private List<Image> imageList = new ArrayList<>();
    private ImageListAdapter imageListAdapter;
    private ProgressDialog progressDialog;


    private AppController(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
//        imLoader = new ImageLoader(requestQueue,
//                new ImageLoader.ImageCache() {
//                    private final LruCache<String, Bitmap>
//                            cache = new LruCache<String, Bitmap>(20);
//
//                    @Override
//                    public Bitmap getBitmap(String url) {
//                        return cache.get(url);
//                    }
//
//                    @Override
//                    public void putBitmap(String url, Bitmap bitmap) {
//                        cache.put(url, bitmap);
//                    }
//                });
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

//    public ImageLoader getImLoader() {
//        getRequestQueue();
//        return this.imLoader;
//    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

//    public void cancelPendingRequest(Object tag) {
//        if (requestQueue != null) {
//            requestQueue.cancelAll(tag);
//        }
//    }

//    public JsonObjectRequest loadImage(final ListView photos_list) {
//
//        JsonObjectRequest job = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                hideDialog();
//                //parsing JSON
//                try {
//                    JSONArray jsonArray = response.getJSONArray("items");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        Image image = new Image();
//                        image.setTitle(jsonObject.getString("title"));
//                        image.setLink(jsonObject.getString("link"));
//                        JSONObject object = jsonObject.getJSONObject("media");
//                        image.setMedia(object.getString("m"));
//                        imageList.add(image);
//                    }
//                    imageListAdapter = new ImageListAdapter(imageList, context.getApplicationContext());
//                    photos_list.setAdapter(imageListAdapter);
//                    imageListAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        return job;
//
//    }

//    public void hideDialog() {
//        if (progressDialog != null) {
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
//    }

}
