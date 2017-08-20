package com.assignment.hansi.photoschoppe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.hansi.photoschoppe.FullScreenActivity;
import com.assignment.hansi.photoschoppe.R;
import com.assignment.hansi.photoschoppe.model.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class ImageListAdapter extends BaseAdapter {
    private ArrayList<Image> imageList;
    private Context context;

    public ImageListAdapter(ArrayList<Image> imageList, Context context) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return imageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context.getApplicationContext(), R.layout.cell_photos, null);
        }
        final ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        TextView titleView = (TextView) view.findViewById(R.id.image_title);

        titleView.setText(imageList.get(i).getTitle());
        final Image image = imageList.get(i);
        Picasso.with(context).load(image.getMedia()).into(imageView);

//        =====================================================================
//        if (imageLoader == null){
//            imageLoader = AppController.getInstance(context.getApplicationContext()).getImLoader();
//
//            //getting data for row
//            imageView.setImageUrl(image.getMedia(),imageLoader);
//            titleView.setText(image.getTitle());}
//        =================================================================

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), FullScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position",image.getIndex());
                intent.putExtra("imageList",imageList);
                context.startActivity(intent);


            }
        });

        return view;
    }
}
