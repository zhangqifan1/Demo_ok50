package com.as.baseinkotlin.ui.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.as.baseinkotlin.ui.TUtil;
import com.as.baseinkotlin.ui.mvp.BaseIModel;
import com.as.baseinkotlin.ui.mvp.BaseIView;
import com.as.baseinkotlin.ui.mvp.BasePresenter;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;

/**
 * -----------------------------
 * Created by zqf on 2018/1/22.
 * ---------------------------
 */

public abstract class BaseMvpActivity<P extends BasePresenter, M extends BaseIModel> extends BaseStatuBarActivity implements BaseIView {

    protected M mMode;
    protected P mPresenter;

    /**
     * 上下文
     */
    protected Context mContext;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        mContext = this;

        initFontScale();
        initView();
        initPresenter();
        initData();
        initListener();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this) && useEventBus()) {//加上判断
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        if (EventBus.getDefault().isRegistered(this) && useEventBus()) {
            EventBus.getDefault().unregister(this);
//            EventBus.getDefault().removeAllStickyEvents();
        }
        super.onDestroy();
    }

    /**
     * 传入布局id
     */
    protected abstract int setLayoutId();

    private void initFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = (float) 1;
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

    /**
     * 屏幕发生改变调用
     */
    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void setContentView(View rootView) {
        super.setContentView(rootView);
    }

    /**
     * 获取资源文件布局
     *
     * @return 资源布局文件layout
     */
    protected abstract int getLayoutId();


    /**
     * 获取传递的bundle数据
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * 初始化view
     */
    public abstract void initView();


    /**
     * 初始化对象
     */
    protected abstract void initData();

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public void initPresenter() {
        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType && ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments().length > 0) {
            mPresenter = TUtil.INSTANCE.getT(this, 0);
            mMode = TUtil.INSTANCE.getT(this, 1);
            if (mPresenter != null) {
                mPresenter.setMV(mMode, this);
            }
        }
    }

    /**
     * 是否使用 {@link EventBus},默认为使用(false)，
     */
    public boolean useEventBus() {
        return false;
    }

    /**
     * 初始化按钮监听
     */
    protected abstract void initListener();

    @NotNull
    @Override
    public Context getCt() {
        return this;
    }

}
