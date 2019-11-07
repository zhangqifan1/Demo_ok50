package com.`as`.wanandroid_kotlin.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import org.jetbrains.anko.dip


/**
 *-----------------------------
 * Created by zqf on 2019/11/4.
 *---------------------------
 */
class ImageAndTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr){

    val text:String="叮咚"
    @SuppressLint("ResourceAsColor")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val mPaint = Paint()
        mPaint.textSize=30f

        //获取字符串的宽高
        val rect = Rect()
        mPaint.getTextBounds(text,0,1,rect)
        val strwidth = rect.width()
        val strheight = rect.height()

        //方法2 获取宽度
        val strwidth2 = mPaint.measureText(text);

        canvas!!.drawText(text, ((width-strwidth2)/2).toFloat(), (height-strheight).toFloat(), mPaint);
    }
}

