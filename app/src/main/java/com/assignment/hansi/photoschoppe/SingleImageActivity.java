package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 31/07/2017.
 */

//@ContentView(R.layout.activity_single_image)
public class SingleImageActivity extends RoboActivity {

    String url;
    String link;

//    @InjectView(R.id.image_view)
//    private ImageView image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("start", "start new activity");
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("media");
        Log.d("url",url);
        link = bundle.getString("link");
        Log.d("link",link);
//        Picasso.with(this).load(url).into(image_view);
//        image_view.setAdjustViewBounds(true);

    }

//    public void shareImage(View view) {
//        Intent mail_intent = new Intent(Intent.ACTION_SEND);
//        mail_intent.setData(Uri.parse("mailTo:"));
//        mail_intent.setType("text/plain");
//        mail_intent.putExtra(Intent.EXTRA_EMAIL," ");
//        mail_intent.putExtra(Intent.EXTRA_CC, " ");
//        mail_intent.putExtra(Intent.EXTRA_SUBJECT, " ");
//        mail_intent.putExtra(Intent.EXTRA_STREAM, url);
//        startActivity(Intent.createChooser(mail_intent, "send mail.."));
//    }

//    public void viewWeb(View view) {
//        Intent web_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
//        web_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(web_intent);
//    }
}
