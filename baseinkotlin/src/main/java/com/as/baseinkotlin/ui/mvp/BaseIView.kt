package com.`as`.baseinkotlin.ui.mvp

import android.content.Context

/**
 * -----------------------------
 * Created by zqf on 2018/1/22.
 * ---------------------------
 */

interface BaseIView {
    val ct: Context
    fun showLoading()
    fun showError()
    fun showSuccess()

}
