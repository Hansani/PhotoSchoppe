package com.assignment.hansi.photoschoppe.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.assignment.hansi.photoschoppe.R;
import com.assignment.hansi.photoschoppe.model.Photographer;

import java.util.List;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class PhotographerListAdapter extends BaseAdapter {
    private Context context;
    private List<Photographer> photographerList;

    //constructor
    public PhotographerListAdapter(Context context, List<Photographer> photographerList) {
        this.context = context;
        this.photographerList = photographerList;
    }

    //override methods
    @Override
    public int getCount() {
        return photographerList.size();
    }

    @Override
    public Object getItem(int i) {
        return photographerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cell_photographer = View.inflate(context.getApplicationContext(), R.layout.cell_photographer, null);


        final TextView first_name = (TextView) cell_photographer.findViewById(R.id.txt_first_name_cell);
        final TextView last_name = (TextView) cell_photographer.findViewById(R.id.txt_last_name_cell);
        final TextView email = (TextView) cell_photographer.findViewById(R.id.txt_email_cell);
        final TextView phone_no = (TextView) cell_photographer.findViewById(R.id.txt_phone_cell);

        first_name.setText(photographerList.get(i).getFirst_name());
        last_name.setText(photographerList.get(i).getLast_name());
        email.setText(photographerList.get(i).getEmail());
        phone_no.setText(photographerList.get(i).getPhone_no());

        phone_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("phone","call the listerner");
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                Log.d("phone","call the intent");
                phoneIntent.setData(Uri.parse("tel:"+phone_no.getText().toString()));
                context.startActivity(Intent.createChooser(phoneIntent,"Dial"));
                Log.d("phone","call the start");

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail_intent = new Intent(Intent.ACTION_SEND);
                mail_intent.setData(Uri.parse("mailTo:"));
                mail_intent.setType("text/plain");
                mail_intent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                mail_intent.putExtra(Intent.EXTRA_SUBJECT, "Hello "+first_name.getText().toString());
                mail_intent.putExtra(Intent.EXTRA_TEXT, "Add your text here");
                context.startActivity(Intent.createChooser(mail_intent, "send mail.."));
            }
        });

        return cell_photographer;
    }

}




