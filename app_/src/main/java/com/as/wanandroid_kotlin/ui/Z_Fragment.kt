package com.`as`.wanandroid_kotlin.ui


import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
import androidx.viewpager.widget.ViewPager
import com.`as`.baseinkotlin.ui.fragment.BaseFragment
import com.`as`.wanandroid_kotlin.R
import com.`as`.wanandroid_kotlin.banner.BannerImageLoader
import com.`as`.wanandroid_kotlin.bean.color.ColorInfo
import com.blankj.utilcode.util.BarUtils.setStatusBarColor
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_z.*
import kotlinx.android.synthetic.main.guide.*
import org.jetbrains.anko.support.v4.viewPager


/**
 *  ZZZZZZ
 */
class Z_Fragment : BaseFragment() {
    val path1 =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573035960317&di=a2c6fada4af4c96847faa81aa99299b9&imgtype=0&src=http%3A%2F%2Fwww.07073.com%2Fuploads%2F150528%2F17598486_162230_1.jpg"
    val path2 =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573035946083&di=8ceab52a85627dc3cfe959b3a49bc5ef&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn21%2F585%2Fw1397h788%2F20180521%2F378f-hawmaua2858921.png"
    val path3 =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573035925037&di=4873852c51b070079553178bec0c1f54&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20131221%2F20131221070816-1190677574.jpg"
    val path4 =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573035909269&di=32b7ce091f9037c1bb63023bc880df5b&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181225%2F81da689c17f048baa2c9713dd677809f.jpeg"
    val path5 =
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=181327252,233054256&fm=26&gp=0.jpg"
    val path6 =
        "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1959359854,2512281822&fm=26&gp=0.jpg"
    val path7 =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573035844941&di=d541b3fe654347d0628a5d7df77b2cfa&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn20115%2F7%2Fw525h282%2F20190106%2F9e5e-hrfcctn1738189.jpg"
    val path8 =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573035780408&di=93648a7e918e68a3e9b5a553bbcb1d92&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fb999a9014c086e0682967f0209087bf40ad1cbb9.jpg"
    val path9 =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573035625953&di=24a4a42d2f2fec443d38529bdc1ccd07&imgtype=0&src=http%3A%2F%2Fp0.ssl.cdn.btime.com%2Ft01abe6ebcebf8a9ac9.jpg%3Fsize%3D870x490"

    val colorlist = mutableListOf<ColorInfo>()
    var imageLoader: BannerImageLoader? = null
    var isInit: Boolean? = true


    override fun setLayoutId(): Int {
        return R.layout.fragment_z;
    }

    override fun initView() {
        initbanner()
        initColorChange();
    }

    private fun initColorChange() {

        banner.viewPager().pageMargin = 40

        banner.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (positionOffset > 1) {//会出现极个别大于1的数据
                    return
                }

                val pos = position % colorlist.size
                val pos1 = (position + 1) % colorlist.size

                val vibrantColor = ColorUtils.blendARGB(
                    imageLoader!!.getVibrantColor(pos),
                    imageLoader!!.getVibrantColor(pos1),
                    positionOffset
                )
                iv_change_color.setBackgroundColor(vibrantColor)
                appbar_change_color.setBackgroundColor(vibrantColor)
            }

            override fun onPageSelected(position: Int) {
                if (isInit == true && iv_change_color != null && appbar_change_color != null) {// 第一次,延时加载才能拿到颜色
                    isInit = false
                    Handler().postDelayed({
                        val vibrantColor = imageLoader!!.getVibrantColor(1)
                        iv_change_color.visibility = View.VISIBLE
                        iv_change_color.setBackgroundColor(vibrantColor)
                        appbar_change_color.setBackgroundColor(vibrantColor)
                    }, 500)

                }
            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        })

    }

    private fun initbanner() {
        val urllist = mutableListOf<String>()
        urllist.add(path1)
        urllist.add(path2)
        urllist.add(path3)
        urllist.add(path4)
        urllist.add(path5)
        urllist.add(path6)
        urllist.add(path7)
        urllist.add(path8)
        urllist.add(path9)
        colorlist.add(ColorInfo(urllist[0]))
        colorlist.add(ColorInfo(urllist[1]))
        colorlist.add(ColorInfo(urllist[2]))
        colorlist.add(ColorInfo(urllist[3]))
        colorlist.add(ColorInfo(urllist[4]))
        colorlist.add(ColorInfo(urllist[5]))
        colorlist.add(ColorInfo(urllist[6]))
        colorlist.add(ColorInfo(urllist[7]))
        colorlist.add(ColorInfo(urllist[8]))

        imageLoader = BannerImageLoader(colorlist)
        banner.setImageLoader(imageLoader)
        //设置图片集合
        banner.setImages(urllist)
        //设置banner动画效果
        // banner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        banner.setDelayTime(3000)
        banner.setOnBannerListener(OnBannerListener {

        })
        //banner设置方法全部调用完毕时最后调用
        banner.start()
    }

    override fun initData() {
    }

    override fun initListener() {

    }


}
