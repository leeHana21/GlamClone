package com.github.hanalee.glamclone.utils

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class UtilFunction {
    companion object {
        const val TAG = "debug"
        const val POSITION = "POSITION"
        const val BODY_TYPE = "BODY_TYPE"
        const val EDUCATION = "EDUCATION"
        const val GLAM_MAIN = 0
        const val NEARBY = 1
        const val FEED = 2

        // http 통신 로그
        fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor { message -> Log.d("http Log : ", message) }
            return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
}