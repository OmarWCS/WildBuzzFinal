package com.example.apprenti.wildbuzz;

/**
 * Created by apprenti on 11/04/17.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;

/**
 * Created by csa on 3/15/2017.
 */

public class Scanner {

    public Bitmap decodeBitmapUri(ChallengeAplay ctx, Uri uri) throws FileNotFoundException {
        int targetW = 1000;
        int targetH = 1000;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;


        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }
}
