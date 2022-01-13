package com.example.main.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.test.R

class TableCellView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val paint: Paint
    private val borderWidth : Float

    private var top = true
    private var left = true
    private var right = true
    private var bottom = true

    init {
        setWillNotDraw(false)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLACK

        // 1 dp
        borderWidth = resources.displayMetrics.density * 1

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TableCellView)

        val value = attributes.getInt(R.styleable.TableCellView_border, 0)

        top = value or 14 == 15
        left = value or 13 == 15
        bottom = value or 11 == 15
        right = value or 7 == 15

        attributes.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val widthF = width.toFloat()
        val heightF = height.toFloat()

        if (top) {
            canvas?.drawRect(0f, 0f, widthF, borderWidth, paint)
        }

        if (left) {
            canvas?.drawRect(0f, 0f, borderWidth, heightF, paint)
        }

        if (bottom) {
            canvas?.drawRect(0f, heightF - borderWidth, widthF, heightF, paint)
        }

        if (right) {
            canvas?.drawRect(widthF - borderWidth, 0f, widthF, heightF, paint)
        }
    }
}