package com.`as`.wanandroid_kotlin.myutil.sharedperference

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.util.Base64

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * AES加密解密工具
 * @author Max
 * 2016年11月25日15:25:17
 */
class EncryptUtil private constructor(context: Context) {

    private val key: String

    init {
        val serialNo = getDeviceSerialNumber(context)
        //加密随机字符串生成AES key
        key = SHA("$serialNo#\$ERDTS\$D%F^Gojikbh")!!.substring(0, 16)
    }

    /**
     * Gets the hardware serial number of this device.
     *
     * @return serial number or Settings.Secure.ANDROID_ID if not available.
     */
    @SuppressLint("HardwareIds")
    private fun getDeviceSerialNumber(context: Context): String {
        // We're using the Reflection API because Build.SERIAL is only available
        // since API Level 9 (Gingerbread, Android 2.3).
        try {
            val deviceSerial = Build::class.java.getField("SERIAL").get(null) as String
            return if (TextUtils.isEmpty(deviceSerial)) {
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            } else {
                deviceSerial
            }
        } catch (ignored: Exception) {
            // Fall back  to Android_ID
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

    }


    /**
     * SHA加密
     * @param strText 明文
     */
    private fun SHA(strText: String?): String? {
        // 返回值
        var strResult: String? = null
        // 是否是有效字符串
        if (strText != null && strText.length > 0) {
            try {
                // SHA 加密开始
                val messageDigest = MessageDigest.getInstance("SHA-256")
                // 传入要加密的字符串
                messageDigest.update(strText.toByteArray())
                val byteBuffer = messageDigest.digest()
                val strHexString = StringBuilder()
                for (aByteBuffer in byteBuffer) {
                    val hex = Integer.toHexString(0xff and aByteBuffer.toInt())
                    if (hex.length == 1) {
                        strHexString.append('0')
                    }
                    strHexString.append(hex)
                }
                strResult = strHexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

        }

        return strResult
    }


    /**
     * AES128加密
     * @param plainText 明文
     */
     fun encrypt(plainText: String): String? {
        try {
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val keyspec = SecretKeySpec(key.toByteArray(), "AES")
            cipher.init(Cipher.ENCRYPT_MODE, keyspec)
            val encrypted = cipher.doFinal(plainText.toByteArray())
            return Base64.encodeToString(encrypted, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * AES128解密
     * @param cipherText 密文
     * @return
     */
     fun decrypt(cipherText: String): String? {
        try {
            val encrypted1 = Base64.decode(cipherText, Base64.NO_WRAP)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val keyspec = SecretKeySpec(key.toByteArray(), "AES")
            cipher.init(Cipher.DECRYPT_MODE, keyspec)
            val original = cipher.doFinal(encrypted1)
            return String(original)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    companion object {
        private var instance: EncryptUtil? = null

        /**
         * 单例模式
         * @param context context
         */
        fun getInstance(context: Context): EncryptUtil {
            if (instance == null) {
                synchronized(EncryptUtil::class.java) {
                    if (instance == null) {
                        instance = EncryptUtil(context)
                    }
                }
            }

            return this.instance!!
        }
    }
}