package com.`as`.wanandroid_kotlin.application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.`as`.baseinkotlin.application.BaseApplication
import com.luliang.shapeutils.DevShapeUtils

/**
 *-----------------------------
 * Created by zqf on 2019/10/24.
 *---------------------------
 */
class App : BaseApplication(){


    override fun initThirdParty() {
        DevShapeUtils.init(this)
    }

    // 防止混乱 jar包冲突
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}