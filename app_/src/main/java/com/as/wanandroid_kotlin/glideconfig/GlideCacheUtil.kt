package com.`as`.wanandroid_kotlin.glideconfig

import android.content.Context
import android.os.Looper
import com.bumptech.glide.Glide

import java.io.File
import java.math.BigDecimal


/**
 * description: Glide清除缓存工具类
 * author: WDSG
 * date: 2017/3/27
 */
class GlideCacheUtil {

    // 图片缓存子目录
    private val CACHEPATH = "image_cache_app"

    // 获取Glide磁盘缓存大小
    val cacheSize: String
        get() {
            try {
                return getFormatSize(getFolderSize(File(mContext!!.cacheDir.toString() + "/" + CACHEPATH)).toDouble())
            } catch (e: Exception) {
                e.printStackTrace()
                return "获取失败"
            }

        }

    // 清除Glide磁盘缓存，自己获取缓存文件夹并删除方法
    fun cleanCatchDisk() {
        deleteFolderFile(mContext!!.cacheDir.toString() + "/" + CACHEPATH, true)
    }

    // 清除图片磁盘缓存，调用Glide自带方法
    fun clearCacheDiskSelf(): Boolean {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Thread(Runnable { Glide.get(mContext!!).clearDiskCache() }).start()
            } else {
                Glide.get(mContext!!).clearDiskCache()
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    // 清除Glide内存缓存
    fun clearCacheMemory(): Boolean {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(mContext!!).clearMemory()
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }


    // 获取指定文件夹内所有文件大小的和
    @Throws(Exception::class)
    private fun getFolderSize(file: File): Long {
        var size: Long = 0
        try {
            val fileList = file.listFiles()
            for (aFileList in fileList!!) {
                if (aFileList.isDirectory) {
                    size = size + getFolderSize(aFileList)
                } else {
                    size = size + aFileList.length()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return size
    }

    // 按目录删除文件夹文件方法
    fun deleteFolderFile(filePath: String, deleteThisPath: Boolean): Boolean {
        try {
            val file = File(filePath)
            if (file.isDirectory) {
                val files = file.listFiles()
                for (file1 in files!!) {
                    deleteFolderFile(file1.absolutePath, true)
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory) {
                    file.delete()
                } else {
                    if (file.listFiles()!!.size == 0) {
                        file.delete()
                    }
                }
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    /**
     * 静态内部类  单独的Object里边全是静态的 方法或者常量 companion 则让他可以不是静态也行
     */
    companion object {

        private var mInstance: GlideCacheUtil? = null
        private var mContext: Context? = null

        fun getInstance(context: Context): GlideCacheUtil {
            if (null == mInstance) {
                mInstance = GlideCacheUtil()
                mContext = context
            }
            return mInstance as GlideCacheUtil
        }

        fun free() {
            mInstance = null
            mContext = null
        }

        // 格式化单位
        fun getFormatSize(size: Double): String {
            val kiloByte = size / 1024
            if (kiloByte < 1) {
                return size.toString() + "Byte"
            }
            val megaByte = kiloByte / 1024
            if (megaByte < 1) {
                val result1 = BigDecimal(java.lang.Double.toString(kiloByte))
                return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
            }
            val gigaByte = megaByte / 1024
            if (gigaByte < 1) {
                val result2 = BigDecimal(java.lang.Double.toString(megaByte))
                return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
            }
            val teraBytes = gigaByte / 1024
            if (teraBytes < 1) {
                val result3 = BigDecimal(java.lang.Double.toString(gigaByte))
                return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
            }
            val result4 = BigDecimal(teraBytes)
            return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
        }
    }
}
