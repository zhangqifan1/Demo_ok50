package com.`as`.baseinkotlin.ui.mvp

import java.lang.ref.WeakReference

/**
 * -----------------------------
 * Created by zqf on 2018/1/22.
 * ---------------------------
 */

class BasePresenter<M : BaseIModel, V : BaseIView> {
    protected var mModel: M? = null
    protected var mView: V? = null
    private var vWeakReference: WeakReference<V>? = null

    fun setMV(m: M, v: V) {
        this.mModel = m
        this.mView = v
        vWeakReference = WeakReference(v)
        onStart()
    }

    /**
     * 开始的方法
     */
    fun onStart() {}

    /**
     * 关闭的方法
     */
    fun onDestroy() {
        if (mModel != null) {
            mModel!!.OnDestroy()
            this.mModel = null
        }
        if (mView != null) {
            vWeakReference!!.clear()
            this.mView = null
        }


    }
}
