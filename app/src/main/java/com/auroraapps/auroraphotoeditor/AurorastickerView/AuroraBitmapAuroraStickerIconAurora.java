package com.auroraapps.auroraphotoeditor.AurorastickerView;

import android.graphics.drawable.Drawable;

public class AuroraBitmapAuroraStickerIconAurora extends AuroraDrawableAuroraSticker {
    private float x;
    private float y;

    public AuroraBitmapAuroraStickerIconAurora(Drawable drawable) {
        super(drawable);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
