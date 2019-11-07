package com.`as`.baseinkotlin.ui.activity

import android.os.Bundle
import me.yokeyword.fragmentation.SupportActivity

/**
 * -----------------------------
 * Created by zqf on 2019/5/29.
 * 设置全局状态栏 颜色
 * ---------------------------
 */
open class BaseStatuBarActivity : SupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val statuBarColor = setStatuBarColor()
        if(statuBarColor != 0){
        }

    }

    /**
     * 设置状态栏颜色 这里设置默认
     * Activity 可以通过重写此方法 设置当前页面状态栏颜色
     * @return
     */
    private fun setStatuBarColor(): Int {
        return 0
    }

}
