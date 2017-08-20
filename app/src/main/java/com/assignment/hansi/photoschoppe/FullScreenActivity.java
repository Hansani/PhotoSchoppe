package com.assignment.hansi.photoschoppe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.assignment.hansi.photoschoppe.adapter.FullScreenAdapter;
import com.assignment.hansi.photoschoppe.model.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apache.velocity.runtime.log.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Hansi on 31/07/2017.
 */

@ContentView(R.layout.activity_full_screen_view)
public class FullScreenActivity extends RoboActivity {

    private FullScreenAdapter fullScreenAdapter;
    private ArrayList<Image> images;
    Image image;

    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private SharedPreferences permissionGranted;
    private Boolean sentToSetting;

    @InjectView(R.id.view_pager)
    private ViewPager view_pager;
    @InjectView(R.id.btn_share)
    private Button shareButton;
    @InjectView(R.id.btn_web)
    private Button webButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        permissionGranted = getSharedPreferences("com.assignment.hansi.userdetail", 0);

        if (ActivityCompat.checkSelfPermission(FullScreenActivity.this, WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(FullScreenActivity.this,
                    WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FullScreenActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app need storage permission");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(FullScreenActivity.this,
                                new String[]{WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else if (permissionGranted.getBoolean(WRITE_EXTERNAL_STORAGE, false)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FullScreenActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app need storage permission");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        sentToSetting = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to permission to Grant Storage",
                                Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(FullScreenActivity.this,
                        new String[]{WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionGranted.edit();
            editor.putBoolean(WRITE_EXTERNAL_STORAGE, Boolean.TRUE);
            editor.commit();
        } else {
            proceedAfterPermission();
        }


        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        images = intent.getParcelableArrayListExtra("imageList");
        fullScreenAdapter = new FullScreenAdapter(FullScreenActivity.this, images, this);

        view_pager.setAdapter(fullScreenAdapter);

        view_pager.setCurrentItem(position);


        //share image
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load(images.get(view_pager.getCurrentItem()).getMedia()).into(target);
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + images.get(view_pager.getCurrentItem()).getTitle() + ".jpg");
                if (file.exists()) {
                    Uri uri = Uri.fromFile(file);
                    Intent mail_intent = new Intent(Intent.ACTION_SEND);
                    mail_intent.setData(Uri.parse("mailTo:"));
                    mail_intent.setType("image/jpeg");
                    mail_intent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(mail_intent, "send mail.."));
                }
            }
        });

        //web image
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent web_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(images.get(view_pager.getCurrentItem()).getLink()));
                web_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(web_intent);
            }
        });

    }

    // proceed After Permission
    private void proceedAfterPermission() {
        Toast.makeText(getApplicationContext(), "You recieved permission to access the storage", Toast.LENGTH_LONG).show();
    }

    //created target
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + images.get(view_pager.getCurrentItem()).getTitle() + ".jpg");
            try {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    //Request Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                proceedAfterPermission();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(FullScreenActivity.this,
                        WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FullScreenActivity.this);
                    builder.setTitle("Need Storage Permission");
                    builder.setMessage("This app needs Storage Permission");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(FullScreenActivity.this,
                                    new String[]{WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to get Permission",
                            Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (ActivityCompat.checkSelfPermission(FullScreenActivity.this,

                    WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sentToSettings) {
            if (ActivityCompat.checkSelfPermission(FullScreenActivity.this, WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }

}
