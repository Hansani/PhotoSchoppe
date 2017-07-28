package com.assignment.hansi.photoschoppe.network.handling;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class VolleyImageDownloadHelper {
    private static VolleyImageDownloadHelper mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleyImageDownloadHelper(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
        //Helper that handles loading and caching images from remote URLs
        mImageLoader = new ImageLoader(mRequestQueue,
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

    public static synchronized VolleyImageDownloadHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyImageDownloadHelper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            //Construct an instance of the DiskBasedCache at the specified directory.
            //rootDirectory The root directory of the cache.
            //maxCacheSizeInBytes The maximum size of the cache in bytes.
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);
            // Set up the network to use HttpURLConnection as the HTTP client.
            //An HttpStack based on HttpURLConnection.
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
