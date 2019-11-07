package com.`as`.wanandroid_kotlin.guide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.`as`.wanandroid_kotlin.MainActivity
import com.`as`.wanandroid_kotlin.R
import kotlinx.android.synthetic.main.guide.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *-----------------------------
 * Created by zqf on 2019/10/24.
 *---------------------------
 */
class GuideActivity :AppCompatActivity(){

    override fun onResume() {
        super.onResume()

        val view = layoutInflater.inflate(R.layout.guide, null)

        window.setContentView(view)

        view.motion.setTransitionListener(object : MotionLayout.TransitionListener {

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                doAsync {
                    Thread.sleep(200)
                    uiThread {
                        val intent = Intent()
                        intent.setClass(this@GuideActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

            }

        })

    }

}