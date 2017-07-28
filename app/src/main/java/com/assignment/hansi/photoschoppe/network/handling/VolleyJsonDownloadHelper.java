package com.assignment.hansi.photoschoppe.network.handling;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class VolleyJsonDownloadHelper {
    private static VolleyJsonDownloadHelper sInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private VolleyJsonDownloadHelper(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }
    public static synchronized VolleyJsonDownloadHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyJsonDownloadHelper(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // Set up the network to use HttpURLConnection as the HTTP client.
            //An HttpStack based on HttpURLConnection.
            //Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = Volley.newRequestQueue(mContext);
            // Don't forget to start the volley request queue
            mRequestQueue.start();
        }
        return mRequestQueue;
    }
}
