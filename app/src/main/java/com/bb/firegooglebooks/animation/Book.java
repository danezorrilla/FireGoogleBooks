package com.bb.firegooglebooks.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.bb.firegooglebooks.R;

public class Book extends View {

    private Bitmap book;
    private float xPos = 0f;
    private float yPos = 0f;

    private float speed = 10.75f;
    private float xSpeed = speed;
    private float ySpeed = speed;

    private Paint paint = new Paint();

    public Book(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        book = BitmapFactory.decodeResource(getResources(), R.drawable.book);
        book = Bitmap.createScaledBitmap(book, (book.getWidth()/2), (book.getHeight()/2), false);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(xPos > (getWidth() - book.getWidth()) || xPos < 0)
            xSpeed *= -1;
        if(yPos > (getHeight() - book.getHeight()) || yPos < 0)
            ySpeed *= -1;

        xPos += xSpeed;
        yPos += ySpeed;

        canvas.drawBitmap(book, xPos, yPos, paint);
        invalidate();
    }

}
