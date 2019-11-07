package com.`as`.baseinkotlin.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.RequiresApi
import org.greenrobot.eventbus.EventBus

/**
 * -----------------------------
 * Created by zqf on 2018/1/22.
 * ---------------------------
 */
abstract class BaseActivity : BaseStatuBarActivity() {

    /**
     * 上下文
     */
    protected var mContext: Context? = null


    @TargetApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        mContext = this
        initFontScale()
        initView()
        initData()
        initListener()
    }


    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this) && useEventBus()) {//加上判断
            EventBus.getDefault().register(this)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this) && useEventBus()) {
            EventBus.getDefault().unregister(this)
            //            EventBus.getDefault().removeAllStickyEvents();
        }
        super.onDestroy()
    }

    private fun initFontScale() {
        val configuration = resources.configuration
        configuration.fontScale = 1.toFloat()
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }

    /**
     * 屏幕发生改变调用
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun setContentView(rootView: View) {
        super.setContentView(rootView)
    }


    /**
     * 传入 布局id
     */
    protected abstract fun setLayoutId(): Int

    /**
     * 初始化view
     */
    protected abstract fun initView()

    /**
     * 初始化对象
     */
    protected abstract fun initData()


    /**
     * 初始化按钮监听
     */
    protected abstract fun initListener()

    /**
     * 是否使用 [EventBus],默认为使用(false)，
     */
    fun useEventBus(): Boolean {
        return false
    }


}