package com.as.baseinkotlin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import me.yokeyword.fragmentation.SupportFragment;
import org.greenrobot.eventbus.EventBus;

/**
 * -----------------------------
 * Created by zqf on 2018/1/22.
 * ---------------------------
 */

public abstract class BaseFragment extends SupportFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        return inflater.inflate(setLayoutId(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isAdded()) {
            if (!EventBus.getDefault().isRegistered(this) && useEventBus()) {//加上判断
                EventBus.getDefault().register(this);
            }
        }
    }

    @Override
    public void onDestroy() {
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

    /**
     * 初始化view
     *
     */
    public abstract void initView();

    /**
     * 初始化对象
     */
    protected abstract void initData();


    /**
     * 初始化按钮监听
     */
    protected abstract void initListener();

    /**
     * 是否使用 {@link EventBus},默认为使用(false)，
     *
     * @return
     */
    public boolean useEventBus() {
        return false;
    }


    public void startActivity(Class clazz) {
        startActivity(new Intent(this.getActivity(), clazz));
    }
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }
}