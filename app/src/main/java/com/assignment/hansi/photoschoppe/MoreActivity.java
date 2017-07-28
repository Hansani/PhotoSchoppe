package com.assignment.hansi.photoschoppe;

import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_more)
public class MoreActivity extends RoboActivity{

    @InjectView(R.id.txt_view_phone_num) private TextView phone_no;
    @InjectView(R.id.txt_view_email_value) private TextView email;



}
