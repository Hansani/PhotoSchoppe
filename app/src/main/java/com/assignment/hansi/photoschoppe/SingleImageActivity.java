package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 31/07/2017.
 */

@ContentView(R.layout.activity_single_image)
public class SingleImageActivity extends RoboActivity {

    private ImageLoader loader = AppController.getInstance(this.getApplicationContext()).getImLoader();
    String url;
    String link;

    @InjectView(R.id.btn_share)
    private Button btn_share;
    @InjectView(R.id.btn_web)
    private Button btn_web;
    @InjectView(R.id.image_view)
    private NetworkImageView image_view;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("image_url");
        link = bundle.getString("link");
        image_view.setImageUrl(url, loader);
        image_view.setAdjustViewBounds(true);

    }

    public void shareImage(View view) {
        Intent mail_intent = new Intent(Intent.ACTION_SEND);
        mail_intent.setData(Uri.parse("mailTo:"));
        mail_intent.setType("text/plain");
        mail_intent.putExtra(Intent.EXTRA_EMAIL," ");
        mail_intent.putExtra(Intent.EXTRA_SUBJECT, " ");
        mail_intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(mail_intent, "send mail.."));
    }

    public void viewWeb(View view) {
        Intent web_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        web_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(web_intent);
    }
}
