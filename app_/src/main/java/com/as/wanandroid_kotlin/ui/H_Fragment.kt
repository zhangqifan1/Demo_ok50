package com.`as`.wanandroid_kotlin.ui


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.`as`.baseinkotlin.ui.fragment.BaseFragment

import com.`as`.wanandroid_kotlin.R
import com.`as`.wanandroid_kotlin.adapter.viewpager.HPageAdapter
import kotlinx.android.synthetic.main.fragment_h_.*


/**
 * A simple [Fragment] subclass.
 *
 */
class H_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_h_, container, false)
    }


}
