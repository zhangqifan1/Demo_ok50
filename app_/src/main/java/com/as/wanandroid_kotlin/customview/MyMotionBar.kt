package com.`as`.wanandroid_kotlin.customview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout

/**
 *-----------------------------
 * Created by zqf on 2019/8/22.
 * 主要还是 实现了AppBarLayout 的这个 滑动监听.
 *---------------------------
 */
class MyMotionBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    /**
     * 以我目前的境界来看   这个类主要是 把MotionLaayout 和 AppbarLayout 关联起来
     * 给他加了一个 addOnOffsetChangedListener
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
    }


}