package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.assignment.hansi.photoschoppe.adapter.FullScreenAdapter;
import com.assignment.hansi.photoschoppe.model.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 31/07/2017.
 */

@ContentView(R.layout.activity_full_screen_view)
public class FullScreenActivity extends RoboActivity {

    private FullScreenAdapter fullScreenAdapter;
    private ArrayList<Image> images;
    Image image;

    @InjectView(R.id.view_pager)
    private ViewPager view_pager;
    @InjectView(R.id.btn_share)
    private Button shareButton;
    @InjectView(R.id.btn_web)
    private Button webButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        images = intent.getParcelableArrayListExtra("imageList");
        fullScreenAdapter = new FullScreenAdapter(FullScreenActivity.this, images, this);

        view_pager.setAdapter(fullScreenAdapter);

        view_pager.setCurrentItem(position);


        //share image
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load(images.get(view_pager.getCurrentItem()).getMedia()).into(target);
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + images.get(view_pager.getCurrentItem()).getTitle() + ".jpg");
                if (file.exists()) {
                    Uri uri = Uri.fromFile(file);
                    Intent mail_intent = new Intent(Intent.ACTION_SEND);
                    mail_intent.setData(Uri.parse("mailTo:"));
                    mail_intent.setType("image/jpeg");
                    mail_intent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(mail_intent, "send mail.."));
                }
            }
        });

        //web image
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent web_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(images.get(view_pager.getCurrentItem()).getLink()));
                web_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(web_intent);
            }
        });

    }

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + images.get(view_pager.getCurrentItem()).getTitle() + ".jpg");
            try {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


}
