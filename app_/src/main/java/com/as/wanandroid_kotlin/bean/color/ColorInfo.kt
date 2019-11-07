package com.`as`.wanandroid_kotlin.bean.color

/**
 * 作者：Zhout
 * 时间：2019/3/12 13:33
 * 描述：banner图片颜色渐变Bean
 *
 *
 * Vibrant （有活力）
 * Vibrant dark（有活力 暗色）
 * Vibrant light（有活力 亮色）
 * Muted （柔和）
 * Muted dark（柔和 暗色）
 * Muted light（柔和 亮色）
 */
class ColorInfo( var imgUrl : String) {

    var vibrantColor = -0x666667
    var vibrantDarkColor = -0x666667
    var vibrantLightColor = -0x666667
    var mutedColor = -0x666667
    var mutedDarkColor = -0x666667
    var mutedLightColor = -0x666667
}
