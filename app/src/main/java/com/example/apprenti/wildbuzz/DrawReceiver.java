package com.example.apprenti.wildbuzz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class DrawReceiver extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 12 ;
    private ImageButton mButtonUpload;
    private ImageView mImageResult;
    private Uri mFilePath;
    private DatabaseReference mDatabase;
    private static String displayName;

    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_receiver);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mButtonUpload = (ImageButton) findViewById(R.id.upload_btn);
        mImageResult = (ImageView) findViewById(R.id.imageViewDrawResult);
        mFilePath = (Uri) getIntent().getParcelableExtra("path");
        mImageResult.setImageBitmap(BitmapFactory.decodeFile(mFilePath.getPath()));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Draws");
        displayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        mButtonUpload.setOnClickListener(this);

    }



    private void uploadDraw() {


        if (mFilePath != null) {

            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle("envoi de ton dessin");
            mProgressDialog.show();

            StorageReference drawRef = mStorageReference.child("images").child("schools").child(displayName).child(mFilePath.getLastPathSegment());
            mDatabase = FirebaseDatabase.getInstance().getReference("images");


            if (!new File(mFilePath.getPath()).exists()) {
                return;
            }

            drawRef.putFile(mFilePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(), "Dessin envoyé !", Toast.LENGTH_LONG).show();
                            mProgressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Quelque chose ne fonctionne pas...", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap;

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null){
            mFilePath = data.getData();

            StorageReference filePath = mStorageReference.child("draw").child(getLocalClassName());

            filePath.putFile(mFilePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(DrawReceiver.this, "succes", Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(DrawReceiver.this, "echec", Toast.LENGTH_LONG).show();
                }
            });

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mFilePath);
                mImageResult.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v == mButtonUpload) {
            uploadDraw();
            mDatabase.child(displayName);
        }
    }



}



