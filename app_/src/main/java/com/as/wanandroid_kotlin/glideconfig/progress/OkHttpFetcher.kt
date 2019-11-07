package com.`as`.wanandroid_kotlin.glideconfig.progress


import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

import java.io.IOException
import java.io.InputStream

class OkHttpFetcher(private val mOkHttpClient: OkHttpClient, private val mGlideUrl: GlideUrl) :
    DataFetcher<InputStream> {
    private var mInputStream: InputStream? = null
    private var mResponseBody: ResponseBody? = null
    @Volatile
    private var mCancelled: Boolean = false

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {

        try {
            val inputStream = loadDataWithRedirects()
            callback.onDataReady(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            callback.onLoadFailed(e)
        }

    }

    @Throws(IOException::class)
    private fun loadDataWithRedirects(): InputStream? {

        val builder = Request.Builder().url(mGlideUrl.toStringUrl())

        for ((key, value) in mGlideUrl.headers) {

            builder.addHeader(key, value)
        }

        val request = builder.build()

        if (mCancelled) {
            return null
        }

        val response = mOkHttpClient.newCall(request).execute()
        mResponseBody = response.body()

        if (!response.isSuccessful || mResponseBody == null) {
            throw IOException("Request failed with code: " + response.code())
        }

        mInputStream = ContentLengthInputStream.obtain(
            mResponseBody!!.byteStream(), mResponseBody!!.contentLength()
        )

        return mInputStream
    }

    override fun cleanup() {

        try {
            if (mInputStream != null) {
                mInputStream!!.close()
            }
            if (mResponseBody != null) {
                mResponseBody!!.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun cancel() {

        mCancelled = true
    }


    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }


    override fun getDataSource(): DataSource {
        return DataSource.REMOTE
    }
}
