package com.assignment.hansi.photoschoppe;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Hansi on 30/07/2017.
 */

public class AppController {
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue requestQueue;
    private ImageLoader imLoader;
    private static AppController mInstance;
    private Context context;


    private AppController(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
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
        return this.imLoader;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequest(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }


}
