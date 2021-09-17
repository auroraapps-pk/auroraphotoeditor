package com.yalantis.ucrop.callback;

import android.net.Uri;
import androidx.annotation.NonNull;

public interface BitmapCropCallback {

    void onBitmapCropped(@NonNull Uri resultUri, int imageWidth, int imageHeight);

    void onCropFailure(@NonNull Throwable t);

}