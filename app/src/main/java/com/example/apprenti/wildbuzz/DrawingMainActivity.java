package com.example.apprenti.wildbuzz;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawingMainActivity extends AppCompatActivity {

    private DrawingView drawView;
    private TextView textViewTimer;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_main);
        drawView = (DrawingView) findViewById(R.id.drawing);
        textViewTimer = (TextView) findViewById(R.id.textViewTimer);


        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewTimer.setText(" " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                endDraw();


            }
        }.start();
    }

        public void endDraw(){

            //timer.start();
            timer.cancel();

            Intent upload = new Intent();
            upload.setClass(DrawingMainActivity.this, DrawReceiver.class);

            Uri filePath = saveDrawnImage(drawView.getDrawnImage());
            upload.putExtra("path", filePath);

            startActivity(upload);

            //finish();



        }


    /** Create a File for saving an image or video */
    private File getOutputMediaFile(){

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }


        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private Uri saveDrawnImage(Bitmap bmp) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            return null;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("Draw", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("Draw", "Error accessing file: " + e.getMessage());
        }
        return Uri.fromFile(pictureFile);

    }
}
