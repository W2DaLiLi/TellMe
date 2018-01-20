package com.example.hzxr.tellme.Util

import android.content.Context
import android.content.Intent
import com.example.hzxr.tellme.UI.LoginActivity

/**
 * Created by Hzxr on 2018/1/20.
 */
object ActivitysUtil {

    fun startActivityToLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}