package com.`as`.wanandroid_kotlin.adapter.viewpager

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 *-----------------------------
 * Created by zqf on 2019/10/28.
 *---------------------------
 */
class PageAdapterMain(fm: FragmentManager, private val list: List<Fragment>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }
}
