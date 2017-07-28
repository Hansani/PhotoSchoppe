package com.assignment.hansi.photoschoppe.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.assignment.hansi.photoschoppe.PortfolioActivity;
import com.assignment.hansi.photoschoppe.R;
import com.assignment.hansi.photoschoppe.model.Image;
import com.assignment.hansi.photoschoppe.network.handling.VolleyImageDownloadHelper;

import java.util.List;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class ImageListAdapter extends BaseAdapter {
    private Context context;
    private List<Image> imageList;

    public ImageListAdapter(Context context, List<Image> imageList) {
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Image image1 = imageList.get(i);
        View cell_photos;
        ImageView imageView = null;
        ImageLoader mImageLoader;

        if (view == null) {
            cell_photos = View.inflate(context, R.layout.cell_photos, null);
        } else cell_photos = view;


        // Instantiate the RequestQueue.
        mImageLoader = VolleyImageDownloadHelper.getInstance(context)
                .getImageLoader();
        //Image URL - This can point to any image file supported by Android
        final String url = image1.getMedia();
        mImageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageURI(Uri.parse(url));
        return null;
    }
}
