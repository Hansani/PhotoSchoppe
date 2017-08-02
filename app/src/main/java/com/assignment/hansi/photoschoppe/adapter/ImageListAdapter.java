package com.assignment.hansi.photoschoppe.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.assignment.hansi.photoschoppe.AppController;
import com.assignment.hansi.photoschoppe.R;
import com.assignment.hansi.photoschoppe.SingleImageActivity;
import com.assignment.hansi.photoschoppe.model.Image;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class ImageListAdapter extends BaseAdapter {
    private List<Image> imageList;
    //private ImageLoader imageLoader;
    private Context context;

    //Response.Listener<JSONObject> activity para in constructor
    public ImageListAdapter(List<Image> imageList, Context context) {
        this.context=context;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
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
                    Intent intent = new Intent(context.getApplicationContext(), SingleImageActivity.class);
                    intent.putExtra("image_url", image.getMedia());
                    intent.putExtra("link", image.getLink());
                    context.startActivity(intent);
                }
            });


      return view;
    }
}
