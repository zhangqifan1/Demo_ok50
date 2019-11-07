package com.`as`.wanandroid_kotlin.adapter.viewpager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.`as`.wanandroid_kotlin.R
import kotlinx.android.synthetic.main.banner_image.view.*

/**
 * -----------------------------
 * Created by zqf on 2019/10/29.
 * ---------------------------
 */
class HPageAdapter(var list: List<Int>,var context : Context) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflate = View.inflate(context, R.layout.banner_image, null)
        inflate.iv.setBackgroundColor(list[position])

        container.addView(inflate)
        return inflate
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }
}
