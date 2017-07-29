package com.assignment.hansi.photoschoppe;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.List;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_portfolio)
public class PortfolioActivity extends RoboActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    class LoadImagesFromFlickr extends AsyncTask<String, Integer, List>{
//        private ProgressDialog progressDialog;
//        private Integer totolCount;
//        private Integer currentIndex;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(PortfolioActivity.this);
//            progressDialog.setMessage("Images are loading from Flickr. Please wait");
//            progressDialog.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            progressDialog.setMessage(String.format("Loading images from Flickr %s/%s. Please wait...",
//                    values[0],values[1]));
//
//        }
//
//        @Override
//        protected List doInBackground(String... strings) {
////            Flickr flickr = new Fli
//            return null;
//        }
//    }

    private void makeRequestWithVolley(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        try {
////                            downloadComplete(RoboGuice.Util.retrieveRepositoriesFromResponse(response)); // 3
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);

    }


}
