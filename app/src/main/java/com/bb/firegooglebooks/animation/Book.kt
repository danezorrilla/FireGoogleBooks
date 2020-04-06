package com.bb.firegooglebooks.animation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.bb.firegooglebooks.R

class Book(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var book: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.book)

    private var xPos = 0f
    private var yPos = 0f

    private val speed = 10.75f
    private var xSpeed = speed
    private var ySpeed = speed
    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (xPos > width - book.width || xPos < 0) xSpeed *= -1f
        if (yPos > height - book.height || yPos < 0) ySpeed *= -1f
        xPos += xSpeed
        yPos += ySpeed
        canvas.drawBitmap(book, xPos, yPos, paint)
        invalidate()
    }

    init {
        book = Bitmap.createScaledBitmap(book, book.width / 2, book.height / 2, false)
    }
}