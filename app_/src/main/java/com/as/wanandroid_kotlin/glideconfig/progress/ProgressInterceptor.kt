package com.`as`.wanandroid_kotlin.glideconfig.progress

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

import java.io.IOException
import java.util.HashMap

class ProgressInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)
        val url = request.url().toString()
        val responseBody = response.body()

        return response.newBuilder().body(
            responseBody?.let { ProgressResponseBody(url, it) }
        ).build()
    }

    companion object {

        private val LISTENER_MAP = HashMap<String, ProgressListener>()

        val listenerMap: Map<String, ProgressListener>
            get() = LISTENER_MAP

        fun addListener(url: String, progressListener: ProgressListener) {
            LISTENER_MAP[url] = progressListener
        }

        fun removeListener(url: String) {
            LISTENER_MAP.remove(url)
        }
    }
}
