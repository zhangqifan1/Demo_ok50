package com.`as`.wanandroid_kotlin.glideconfig.progress

import android.util.Log
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

import java.io.IOException

class ProgressResponseBody(url: String, private val mResponseBody: ResponseBody) : ResponseBody() {

    private var mBufferedSource: BufferedSource? = null
    private var mProgressListener: ProgressListener? = null

    init {
        mProgressListener = ProgressInterceptor.listenerMap[url]
    }

    override fun contentType(): MediaType? {
        return mResponseBody.contentType()
    }

    override fun contentLength(): Long {
        return mResponseBody.contentLength()
    }

    override fun source(): BufferedSource {

        if (mBufferedSource == null) {
            mBufferedSource = Okio.buffer(ProgressSource(mResponseBody.source()))
        }

        return this.mBufferedSource!!
    }

    private inner class ProgressSource(delegate: Source) : ForwardingSource(delegate) {

        private var mTotalBytesRead: Long = 0
        private var mCurrentProgress: Int = 0

        @Throws(IOException::class)
        override fun read(sink: Buffer, byteCount: Long): Long {

            val bytesRead = super.read(sink, byteCount)
            val fullLength = mResponseBody.contentLength()

            if (bytesRead == -1L) {
                mTotalBytesRead = fullLength
            } else {
                mTotalBytesRead += bytesRead
            }

            val progress = (100f * mTotalBytesRead / fullLength).toInt()

            if (mProgressListener != null && progress != mCurrentProgress) {
                mProgressListener!!.onProgress(progress)
            }

            if (mProgressListener != null && mTotalBytesRead == fullLength) {
                mProgressListener = null
            }

            mCurrentProgress = progress

            return bytesRead
        }
    }

}
