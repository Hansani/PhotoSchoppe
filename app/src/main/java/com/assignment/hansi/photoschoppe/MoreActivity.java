package com.assignment.hansi.photoschoppe;

import android.*;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static com.assignment.hansi.photoschoppe.R.string.phone_no;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_more)
public class MoreActivity extends RoboActivity implements OnMapReadyCallback {

    private GoogleMap locationMap;
    private Context context;

    @InjectView(R.id.txt_view_phone_num)
    private TextView phone_no;
    @InjectView(R.id.txt_view_email_value)
    private TextView email;
    @InjectView(R.id.mapView)
    private MapView location_map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:" + phone_no.getText().toString()));
                if (ActivityCompat.checkSelfPermission(context.getApplicationContext(),
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(phoneIntent);

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mailintent = new Intent(Intent.ACTION_SEND);
                mailintent.setData(Uri.parse("mailTo:"));
                mailintent.setType("text/plain");
                mailintent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                mailintent.putExtra(Intent.EXTRA_SUBJECT, " ");
                mailintent.putExtra(Intent.EXTRA_TEXT, "Add your text here");
                startActivity(Intent.createChooser(mailintent, "send mail.."));
            }
        });


    }

    public void loadMap(View view) {
        location_map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.locationMap = googleMap;
        LatLng location = new LatLng(30.395414, -97.753739);
        locationMap.addMarker(new MarkerOptions().position(location).title("PhotoSchoppe"));
        locationMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }
}