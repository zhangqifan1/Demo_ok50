package com.`as`.wanandroid_kotlin.myutil.toast

import android.graphics.Color
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.ToastUtils.setGravity
import com.blankj.utilcode.util.ToastUtils.setMsgColor

/**
 * -----------------------------
 * Created by zqf on 2019/10/24.
 * ---------------------------
 */
object ToastUtil {
    fun show(str:String){
        // 居中黑底白字
        ToastUtils.setBgColor(-0x61000000)
        ToastUtils.setMsgColor(Color.parseColor("#ffffff"))
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)

        ToastUtils.showShort(str)
    }
}
