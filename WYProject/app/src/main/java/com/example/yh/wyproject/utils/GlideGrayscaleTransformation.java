package com.example.yh.wyproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 * Author: gaoxu
 * TIME: 2017/8/31
 * Description: This is GlideGrayscaleTransformation
 * Function:图片置灰
 */

public class GlideGrayscaleTransformation implements Transformation<Bitmap> {
    private BitmapPool mBitmapPool;

    public GlideGrayscaleTransformation(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public GlideGrayscaleTransformation(BitmapPool pool) {
        mBitmapPool = pool;
    }

    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();

        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap.Config config =
                source.getConfig() != null ? source.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap bitmap = mBitmapPool.get(width, height, config);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, config);
        }

        Canvas canvas = new Canvas(bitmap);
        ColorMatrix saturation = new ColorMatrix();
        saturation.setSaturation(0f);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(saturation));
        canvas.drawBitmap(source, 0, 0, paint);

        return BitmapResource.obtain(bitmap, mBitmapPool);
    }

    @Override public String getId() {
        return "GlideGrayscaleTransformation()";
    }
}