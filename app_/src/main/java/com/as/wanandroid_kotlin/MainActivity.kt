package com.`as`.wanandroid_kotlin

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.`as`.baseinkotlin.ui.activity.BaseActivity
import com.`as`.wanandroid_kotlin.adapter.viewpager.PageAdapterMain
import com.`as`.wanandroid_kotlin.myutil.toast.ToastUtil
import com.`as`.wanandroid_kotlin.ui.H_Fragment
import com.`as`.wanandroid_kotlin.ui.Z_Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.gyf.immersionbar.ImmersionBar
import com.luliang.shapeutils.DevShapeUtils
import com.luliang.shapeutils.shape.DevShape
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_drawer.*
import kotlinx.android.synthetic.main.fragment_z.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast


class MainActivity : BaseActivity() {

    var isClick = false

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        ImmersionBar.with(this).transparentBar().init();
        //左侧菜单的 配置
        initDrawer();

    }

    /**
     * 初始化左侧菜单配置
     */
    private fun initDrawer() {

        DevShapeUtils.shape(DevShape.RECTANGLE)
            .radius(12f)
            .line(1, R.color.black)
            .solid(R.color.silver)
            .into(change_usericon)
        DevShapeUtils.shape(DevShape.RECTANGLE)
            .radius(12f)
            .line(1, R.color.black)
            .solid(R.color.silver)
            .into(change_username)

        //设置顶部的模糊
        Glide.with(this)
            .load(R.drawable.drawerbg)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(14, 4)))
            .into(drawerbg)

        drawerbg.setColorFilter(Color.parseColor("#4dffffff"), PorterDuff.Mode.LIGHTEN)

        //设置顶部的模糊
        Glide.with(this)
            .load(R.drawable.drawerbg)
            .into(user_icon)


        //设置诗词 字体
        tv1.paint.isFakeBoldText = true
        tv2.paint.isFakeBoldText = true
        tv3.paint.isFakeBoldText = true
        tv4.paint.isFakeBoldText = true
        tv5.paint.isFakeBoldText = true

    }

    override fun initData() {
        val mFragments = mutableListOf<Fragment>()

        mFragments.add(Z_Fragment())
        mFragments.add(H_Fragment())
        mFragments.add(Z_Fragment())
        mFragments.add(Z_Fragment())
        mFragments.add(Z_Fragment())

        //开启或关闭点击动画(文字放大效果和图片移动效果)。默认为 true.
//        bnve.enableAnimation(false);
//        bnve.enableShiftingMode(false)
        //去除第三个条目的点击效果
        bnve.getBottomNavigationItemView(2).setBackgroundDrawable(null)


        val pageAdapterMain = PageAdapterMain(supportFragmentManager, mFragments)
        main_vp.adapter = pageAdapterMain

        bnve.setOnNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener,

            BottomNavigationView.OnNavigationItemSelectedListener {

            private var previousPosition = -1

            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var position = 0
                when (item.getItemId()) {
                    R.id.icon1 -> position = 0
                    R.id.icon2 -> position = 1
                    R.id.i_empty -> position = 2
                    R.id.icon3 -> position = 3
                    R.id.icon4 -> position = 4
                }
                if (previousPosition != position) {
                    main_vp.setCurrentItem(position, false)
                    previousPosition = position
                }
                return true
            }
        })

        // 针对悬浮按钮
        main_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                var numPages = 5//总的页数
//                var progress = (position + positionOffset) / (numPages - 1)
//                my_viewpager_head.setProgress(progress)

            }

            override fun onPageSelected(position: Int) {
//                var aposition = position
//
//                if (aposition == 2) {// 2 is center
//                    aposition++// if page is 2, need set bottom item to 3, and the same to 3 -> 4
//                }
                if (position == 2) {
                    fab.setImageResource(R.drawable.a_ture)
                } else {
                    fab.setImageResource(R.drawable.a_false)
                }
                bnve.setCurrentItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })


        fab.setOnClickListener {
            bnve.setCurrentItem(2)
        }


        ivfloat.onClick {
            isClick = !isClick;
            val stateSet = intArrayOf(android.R.attr.state_checked * if (isClick) 1 else -1)
            ivfloat.setImageState(stateSet, true)

            main_drawer.openDrawer(Gravity.LEFT)
        }


        main_drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerClosed(drawerView: View) {
                ivfloat.setImageState(intArrayOf(1), true)
            }

            override fun onDrawerOpened(drawerView: View) {
            }
        })


    }

    override fun initListener() {

    }
}
