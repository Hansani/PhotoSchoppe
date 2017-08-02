package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_more)
public class MoreActivity extends RoboActivity {

    @InjectView(R.id.txt_view_phone_num)
    private TextView phone_no;
    @InjectView(R.id.txt_view_email_value)
    private TextView email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:" + phone_no.getText().toString()));
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
//                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
                startActivity(Intent.createChooser(phoneIntent,"Dial"));

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
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}