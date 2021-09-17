package com.auroraapps.auroraphotoeditor.AuroramultiTouchLib;

import android.annotation.SuppressLint;
import android.graphics.PointF;

@SuppressLint("ParcelCreator")
public class AuroraVector2D extends PointF {

    public AuroraVector2D() {
        super();
    }

    public AuroraVector2D(float x, float y) {
        super(x, y);
    }

    public static float getAngle(AuroraVector2D vector1, AuroraVector2D vector2) {
        vector1.normalize();
        vector2.normalize();
        double degrees = (180.0 / Math.PI) * (Math.atan2(vector2.y, vector2.x) - Math.atan2(vector1.y, vector1.x));
        return (float) degrees;
    }

    public void normalize() {
        float length = (float) Math.sqrt(x * x + y * y);
        x /= length;
        y /= length;
    }
}