package com.`as`.wanandroid_kotlin.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.`as`.wanandroid_kotlin.R
import com.luliang.shapeutils.DevShapeUtils
import com.luliang.shapeutils.shape.DevShape

/**
 *-----------------------------
 * Created by zqf on 2019/10/28.
 *---------------------------
 */
class ShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 默认颜色
    var thecolor: Int = R.color.white

    //默认形状
    var theshape: Int = DevShape.OVAL

    init {
        DevShapeUtils.shape(theshape)
            .radius(50F)
            .solid(this.thecolor)
            .line(1, R.color.white)
            .into(this)
    }

}