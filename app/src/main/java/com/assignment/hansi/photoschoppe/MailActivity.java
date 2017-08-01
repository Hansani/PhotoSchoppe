package com.assignment.hansi.photoschoppe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 28/07/2017.
 */

@ContentView(R.layout.activity_email)
public class MailActivity extends RoboActivity{

    String receive, subject, message;
    Context context;

    @InjectView(R.id.edit_txt_to)
    private EditText email_to;
    @InjectView(R.id.edit_txt_subject)
    private EditText email_subject;
    @InjectView(R.id.edit_txt_message)
    private EditText email_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        receive = bundle.getString("email_address");
        subject = bundle.getString("subject");
        message = bundle.getString("message");

        email_to.setText(receive);
        email_subject.setText(subject);
        email_message.setText(message);
    }

    public void onClick(View view) {
        String receive = email_to.getText().toString();
        String subject = email_subject.getText().toString();
        String message = email_message.getText().toString();
        Intent mailintent = new Intent(Intent.ACTION_SEND);
        mailintent.setData(Uri.parse("mailTo:"));
        mailintent.setType("text/plain");
        mailintent.putExtra(Intent.EXTRA_EMAIL, receive);
        mailintent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailintent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(mailintent, "send mail.."));
    }




}
