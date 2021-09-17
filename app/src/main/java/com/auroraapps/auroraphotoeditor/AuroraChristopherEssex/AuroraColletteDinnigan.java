package com.auroraapps.auroraphotoeditor.AuroraChristopherEssex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.InputStream;

public class AuroraColletteDinnigan {


    public interface LoadImageCallback {
        public void callback(Bitmap result);
    }
    
    public static void asyncLoadSmallImage(Context context, Uri imageUri, LoadImageCallback callback) {
        new LoadSmallPicTask(context, imageUri, callback).execute();
    }

    private static class LoadSmallPicTask extends AsyncTask<Void, Void, Bitmap> {

        private final Uri         imageUri;
        private final Context     context;
        private LoadImageCallback callback;

        public LoadSmallPicTask(Context context, Uri imageUri, LoadImageCallback callback) {
            this.imageUri = imageUri;
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            return getResizedBitmap(context, imageUri, 300, 300);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            callback.callback(result);
        }

    }

    public static Bitmap getResizedBitmap(Context context, Uri imageUri, int width, int height) {
        InputStream inputStream = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            inputStream = context.getContentResolver().openInputStream(imageUri);
            BitmapFactory.decodeStream(inputStream, null, options);

            options.outWidth = width;
            options.outHeight = height;
            options.inJustDecodeBounds = false;
            AuroraLeonaEdmiston.closeStream(inputStream);
            inputStream = context.getContentResolver().openInputStream(imageUri);
            return BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            AuroraLeonaEdmiston.closeStream(inputStream);
        }
        return null;
    }
}
