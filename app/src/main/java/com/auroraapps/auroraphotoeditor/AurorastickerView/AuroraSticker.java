package com.auroraapps.auroraphotoeditor.AurorastickerView;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

public abstract class AuroraSticker {
    protected static final String TAG = "Sticker";

    protected Matrix mMatrix;
    protected boolean mIsFlipped;

    public boolean isFlipped() {
        return mIsFlipped;
    }

    public void setFlipped(boolean flipped) {
        mIsFlipped = flipped;
    }

    public Matrix getMatrix() {
        return mMatrix;
    }

    public void setMatrix(Matrix matrix) {
        mMatrix.set(matrix);
    }

    public abstract void draw(Canvas canvas);

    public abstract int getWidth();

    public abstract int getHeight();

    public float[] getBoundPoints() {
        if (!mIsFlipped) {
            return new float[]{
                    0f, 0f,
                    getWidth(), 0f,
                    0f, getHeight(),
                    getWidth(), getHeight()
            };
        } else {
            return new float[]{
                    getWidth(), 0f,
                    0f, 0f,
                    getWidth(), getHeight(),
                    0f, getHeight()
            };
        }
    }

    public float[] getMappedBoundPoints() {
        float[] dst = new float[8];
        mMatrix.mapPoints(dst, getBoundPoints());
        return dst;
    }

    public float[] getMappedPoints(float[] src) {
        float[] dst = new float[src.length];
        mMatrix.mapPoints(dst, src);
        return dst;
    }


    public RectF getBound() {
        return new RectF(0, 0, getWidth(), getHeight());
    }

    public RectF getMappedBound() {
        RectF dst = new RectF();
        mMatrix.mapRect(dst, getBound());
        return dst;
    }

    public PointF getCenterPoint() {
        return new PointF(getWidth() / 2, getHeight() / 2);
    }

    public PointF getMappedCenterPoint() {
        PointF pointF = getCenterPoint();
        float[] dst = getMappedPoints(new float[]{
                pointF.x,
                pointF.y
        });
        return new PointF(dst[0], dst[1]);
    }

    public void release() {
    }

}
