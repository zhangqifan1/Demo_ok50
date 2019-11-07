//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.`as`.wanandroid_kotlin.customview

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView

@SuppressLint("AppCompatCustomView")
class DragFloatButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    ImageView(context, attrs, defStyleAttr) {
    private var parentHeight: Int = 0
    private var parentWidth: Int = 0
    private var lastX: Int = 0
    private var lastY: Int = 0
    private var isDrag: Boolean = false
    private var parent: ViewGroup? = null

    private val isNotDrag: Boolean
        get() = !this.isDrag && (this.x == 0.0f || this.x == (this.parentWidth - this.width).toFloat())

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val rawX = event.rawX.toInt()
        val rawY = event.rawY.toInt()
        when (event.action and 255) {
            0 -> {
                this.isPressed = true
                this.isDrag = false
                this.getParent().requestDisallowInterceptTouchEvent(true)
                this.lastX = rawX
                this.lastY = rawY
                if (this.getParent() != null) {
                    this.parent = this.getParent() as ViewGroup
                    this.parentHeight = this.parent!!.height
                    this.parentWidth = this.parent!!.width
                }
            }
            1 -> if (!this.isNotDrag) {
                this.isPressed = false
                this.moveHide(rawX)
            } else {

            }
            2 -> if (this.parentHeight.toDouble() > 0.2 && this.parentWidth.toDouble() > 0.2) {
                this.isDrag = true
                val dx = rawX - this.lastX
                val dy = rawY - this.lastY
                val distance = Math.sqrt((dx * dx + dy * dy).toDouble()).toInt()
                if (distance == 0) {
                    this.isDrag = false
                } else {
                    var x = this.x + dx.toFloat()
                    var y = this.y + dy.toFloat()
                    x =
                        if (x < 0.0f) 0.0f else if (x > (this.parentWidth - this.width).toFloat()) (this.parentWidth - this.width).toFloat() else x
                    y =
                        if (this.y < 0.0f) 0.0f else if (this.y + this.height.toFloat() > this.parentHeight.toFloat()) (this.parentHeight - this.height).toFloat() else y
                    this.x = x
                    this.y = y
                    this.lastX = rawX
                    this.lastY = rawY
                }
            } else {
                this.isDrag = false
            }
        }

        return !this.isNotDrag || super.onTouchEvent(event)
    }

    private fun moveHide(rawX: Int) {
        if (rawX >= this.parentWidth / 2) {
            this.animate().setInterpolator(DecelerateInterpolator()).setDuration(500L)
                .xBy((this.parentWidth - this.width).toFloat() - this.x).start()
        } else {
            val oa = ObjectAnimator.ofFloat(this, "x", *floatArrayOf(this.x, 0.0f))
            oa.interpolator = DecelerateInterpolator()
            oa.duration = 500L
            oa.start()
        }

    }


    val text:String="叮咚"
    /**
     * 手动添加文字
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        val mPaint = Paint()
//        mPaint.textSize=60f
//
//        //获取字符串的宽高
//        val rect = Rect()
//        mPaint.getTextBounds(text,0,1,rect)
//        val strwidth = rect.width()
//        val strheight = rect.height()
//
//        //方法2 获取宽度
//        val strwidth2 = mPaint.measureText(text);
//
//        //略有偏差可自行调整
////        canvas!!.drawText(text, ((width-strwidth2)/2).toFloat(), (height-strheight).toFloat()+20, mPaint);
//        canvas!!.drawText(text, ((width-strwidth2)/2).toFloat(), ((height-strheight)/2).toFloat(), mPaint);

    }

}
