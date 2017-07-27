package com.assignment.hansi.photoschoppe;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.net.HttpURLConnection;

/**
 * Created by Hansi on 27/07/2017.
 */

public class RequestQueues {
    private static RequestQueues requestQueue;
    private RequestQueue queue;
    private ImageLoader loader;
    private static Context mcontext;

    private RequestQueues(Context context) {
        mcontext = context;
        queue = getRequestQueue();
        loader = new ImageLoader(queue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized RequestQueues getInstance(Context context) {
        if (requestQueue == null) {
            requestQueue = new RequestQueues(context);
        }
        return requestQueue;
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            queue = Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return loader;
    }

    public void cancelPendingRequests(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
        }
    }
}

