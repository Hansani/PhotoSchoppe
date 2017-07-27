package com.assignment.hansi.photoschoppe;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_directory)
public class DirectoryActivity extends RoboActivity {

    public class Placeholders {
        TextView first_name;
        TextView last_name;
        TextView email;
        TextView phone_no;
    }

    @InjectView(R.id.photographer_list)
    private ListView photographer_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHandler handler = new DBHandler(this);

        File database = getApplicationContext().getDatabasePath(DBHandler.DB_NAME);
        if (!database.exists()) {
            handler.getReadableDatabase();
            if (copyDatabase(this)) {
                Toast.makeText(this, "copy database successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "copy database failed", Toast.LENGTH_SHORT).show();
            }
        }

        final Photographer[] photographers = handler.getAllPGs();

        photographer_list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return photographers.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                Photographer photographer = photographers[i];
                View cell_photographer;

                if (view == null) {
                    cell_photographer = LayoutInflater.from(DirectoryActivity.this).inflate(R.layout.cell_photographer, null);
                } else {
                    cell_photographer = view;
                }

                Placeholders placeholders = (Placeholders) cell_photographer.getTag();
                TextView first_name;
                TextView last_name;
                TextView email;
                final TextView phone_no;

                if (placeholders == null) {
                    first_name = (TextView) cell_photographer.findViewById(R.id.txt_first_name_cell);
                    last_name = (TextView) cell_photographer.findViewById(R.id.txt_last_name_cell);
                    email = (TextView) cell_photographer.findViewById(R.id.txt_email_cell);
                    phone_no = (TextView) cell_photographer.findViewById(R.id.txt_phone_cell);

                    placeholders = new Placeholders();
                    placeholders.first_name = first_name;
                    placeholders.last_name = last_name;
                    placeholders.email = email;
                    placeholders.phone_no = phone_no;

                    cell_photographer.setTag(placeholders);
                } else {
                    first_name = placeholders.first_name;
                    last_name = placeholders.last_name;
                    email = placeholders.email;
                    phone_no = placeholders.phone_no;
                }

                first_name.setText(photographer.getFirst_name());
                last_name.setText(photographer.getLast_name());
                email.setText(photographer.getEmail());
                phone_no.setText(photographer.getPhone_no());

                final String phone = phone_no.getText().toString().replaceAll("-", "");
                final String email_ad = email.getText().toString();


                phone_no.setOnClickListener(new View.OnClickListener() {
                    Context context;
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse(phone));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(callIntent);
                    }
                });

                email.setOnClickListener(new View.OnClickListener() {
                    Context context;
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("email_address",email_ad);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });


                return cell_photographer;            }
        });

    }

    private boolean copyDatabase(Context context){
        try {
            InputStream inputStream = context.getAssets().open(DBHandler.DB_NAME);
            OutputStream outputStream = new FileOutputStream(DBHandler.DB_NAME);
            byte[] bytes = new byte[1024];
            int n = 0;
            while (inputStream.read(bytes)>0){
                outputStream.write(bytes,0,n);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
