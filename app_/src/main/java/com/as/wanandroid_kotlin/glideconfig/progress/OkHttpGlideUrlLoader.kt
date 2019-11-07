package com.`as`.wanandroid_kotlin.glideconfig.progress


import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import okhttp3.OkHttpClient

import java.io.InputStream

class OkHttpGlideUrlLoader(private val mOkHttpClient: OkHttpClient) : ModelLoader<GlideUrl, InputStream> {

    override fun buildLoadData(
        glideUrl: GlideUrl,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(glideUrl, OkHttpFetcher(mOkHttpClient, glideUrl))
    }

    override fun handles(glideUrl: GlideUrl): Boolean {
        return true
    }

    class Factory : ModelLoaderFactory<GlideUrl, InputStream> {

        private var mOkHttpClient: OkHttpClient? = null

        val okHttpClient: OkHttpClient
            @Synchronized get() {

                if (mOkHttpClient == null) {
                    mOkHttpClient = OkHttpClient()
                }

                return mOkHttpClient as OkHttpClient
            }

        constructor() {}

        constructor(okHttpClient: OkHttpClient) {
            mOkHttpClient = okHttpClient
        }


        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideUrl, InputStream> {
            return OkHttpGlideUrlLoader(okHttpClient)
        }

        override fun teardown() {

        }
    }
}
