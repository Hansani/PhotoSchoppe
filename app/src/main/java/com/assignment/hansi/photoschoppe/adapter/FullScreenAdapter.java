package com.assignment.hansi.photoschoppe.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.assignment.hansi.photoschoppe.R;
import com.assignment.hansi.photoschoppe.model.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hansanibiyanwila on 8/10/17.
 */

public class FullScreenAdapter extends PagerAdapter {
    private Activity activity_1;
    private ArrayList<Image> images;
    private LayoutInflater  layoutInflater;
    private Context context;

    public FullScreenAdapter(Activity activity, ArrayList<Image> imagesLink, Context context) {
        this.activity_1 = activity;
        this.images = imagesLink;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView;

        layoutInflater = (LayoutInflater) activity_1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fullScreenView = layoutInflater.inflate(R.layout.single_image,container,false);

        imageView = (ImageView) fullScreenView.findViewById(R.id.image_view);

        Picasso.with(context).load(images.get(position).getMedia()).into(imageView);

        container.addView(fullScreenView);

        return fullScreenView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
