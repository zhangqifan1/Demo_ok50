package com.`as`.wanandroid_kotlin.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.drawerlayout.widget.DrawerLayout

/**
 *-----------------------------
 * 将MotionLayout 与 DrawerLayout 关联起来
 *---------------------------
 */
class MyMotionDrawer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), DrawerLayout.DrawerListener{
    override fun onDrawerStateChanged(p0: Int) {

    }

    override fun onDrawerSlide(p0: View, p1: Float) {
        progress=p1
    }

    override fun onDrawerClosed(p0: View) {

    }

    override fun onDrawerOpened(p0: View) {
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as DrawerLayout)?.addDrawerListener(this)
    }
}