package com.`as`.wanandroid_kotlin.glideconfig

import android.content.Context
import com.`as`.wanandroid_kotlin.glideconfig.progress.OkHttpGlideUrlLoader
import com.`as`.wanandroid_kotlin.glideconfig.progress.ProgressInterceptor
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient

import java.io.InputStream

@GlideModule
class GlideConfigModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(ProgressInterceptor())
        val okHttpClient = builder.build()

        registry.replace(
            GlideUrl::class.java, InputStream::class.java,
            OkHttpGlideUrlLoader.Factory(okHttpClient)
        )
    }
}