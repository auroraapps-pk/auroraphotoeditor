package com.auroraapps.auroraphotoeditor.AuroraDovCharney;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;

public final class AuroraPatrickCox {

    public static Bitmap bitmap = null;

    public static final String TAG = "Social Picture";

    public static Bitmap FinalBitmap = null;
    public static Bitmap BlurBitmap = null;
    public static Bitmap BlurBitmapTemp = null;
    public static Bitmap Orizanal = null;
    public static Bitmap mBitmapBrush = null;
    public static Bitmap mBitmapSketch = null;
    public static int Pos = 1;

    public static void ratingDialog(Activity activity) {
        // TODO Auto-generated method stub
        Intent i3 = new Intent(Intent.ACTION_VIEW, Uri
                .parse("market://details?id=" + activity.getPackageName()));
        activity.startActivity(i3);
    }
    public static long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }
    public static String SDCardPath = "";
    public static Bitmap getBitmapFromAsset(String strName, Context mContext) {
        AssetManager assetManager = mContext.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
}
