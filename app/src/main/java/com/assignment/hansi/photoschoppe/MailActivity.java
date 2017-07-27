package com.assignment.hansi.photoschoppe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInstaller;
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
public class MailActivity extends RoboActivity implements View.OnClickListener {

    Session session;
    ProgressDialog progressDialog;
    String recieve, subject, message;
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
        String email = bundle.getString("email_address");
        email_to.setText(email);
    }

    @Override
    public void onClick(View view) {
        recieve = email_to.getText().toString();
        subject = email_subject.getText().toString();
        message = email_message.getText().toString();

        Properties properties = new Properties();
        properties.put("mail.stmp.host", "stmp.gmial.com");
        properties.put("mail.stmp.socketFactory.port", "465");
        properties.put("mail.stmp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.stmp.auth", "true");
        properties.put("mail.stmp.port", "465");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hansanib@irononetech.com", "12345678");
            }
        });

        ProgressDialog.show(context, "", "sending mail..", true);

        RecieveFeedBackTask backTask = new RecieveFeedBackTask();
        backTask.execute();
    }

    class RecieveFeedBackTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Message message1 = new MimeMessage(session);
                message1.setFrom(new InternetAddress("hansanib@irononetech.com"));
                message1.setRecipient(Message.RecipientType.TO, new InternetAddress(recieve));
                message1.setSubject(subject);
                message1.setContent(message, "text/html; charset=utf-8");
                Transport.send(message1);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            progressDialog.dismiss();
            email_to.setText("");
            email_subject.setText("");
            email_message.setText("");
            Toast.makeText(getApplicationContext(), "Message send successfully.", Toast.LENGTH_SHORT).show();
        }
    }
}
