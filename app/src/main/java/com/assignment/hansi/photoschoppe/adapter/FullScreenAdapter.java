package com.assignment.hansi.photoschoppe.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.assignment.hansi.photoschoppe.R;

import java.util.ArrayList;

/**
 * Created by hansanibiyanwila on 8/10/17.
 */

public class FullScreenAdapter extends PagerAdapter {
    private Activity activity_1;
    private ArrayList<String> imagesLink;
    private LayoutInflater  layoutInflater;
    private Context context;

    public FullScreenAdapter(Activity activity, ArrayList<String> imagesLink, Context context) {
        this.activity_1 = activity;
        this.imagesLink = imagesLink;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagesLink.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView;
        Button shareButton;
        Button webButton;

        layoutInflater = (LayoutInflater) activity_1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fullScreenView = layoutInflater.inflate(R.layout.activity_single_image,container,false);

        imageView = (ImageView) fullScreenView.findViewById(R.id.image_view);
        shareButton = (Button) fullScreenView.findViewById(R.id.btn_share);
        webButton = (Button) fullScreenView.findViewById(R.id.btn_web);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(imagesLink.get(position), options);
        imageView.setImageBitmap(bitmap);

        //share image
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail_intent = new Intent(Intent.ACTION_SEND);
                mail_intent.setData(Uri.parse("mailTo:"));
                mail_intent.setType("text/plain");
                mail_intent.putExtra(Intent.EXTRA_EMAIL," ");
                mail_intent.putExtra(Intent.EXTRA_CC, " ");
                mail_intent.putExtra(Intent.EXTRA_SUBJECT, " ");
                mail_intent.putExtra(Intent.EXTRA_STREAM, imagesLink.get(position));
                context.startActivity(Intent.createChooser(mail_intent, "send mail.."));
            }
        });

        //web image
        Intent web_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagesLink.get(position)));
        web_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(web_intent);

        container.addView(fullScreenView);


        return fullScreenView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
