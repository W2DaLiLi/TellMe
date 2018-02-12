package com.example.hzxr.tellme.Util

import android.content.Context
import android.content.Intent
import com.example.hzxr.tellme.ui.AddFriendActivity
import com.example.hzxr.tellme.ui.HomeActivity
import com.example.hzxr.tellme.ui.LoginActivity
import com.example.hzxr.tellme.ui.RegisterActivity

/**
 * Created by Hzxr on 2018/1/20.
 */
object ActivitysUtil {

    fun startActivityToLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    fun startActivityToRegister(context: Context) {
        val intent = Intent(context, RegisterActivity::class.java)
        context.startActivity(intent)
    }

    fun startActivityToHome(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }

    fun startActivityToAddFriend(context: Context) {
        val intent = Intent(context, AddFriendActivity::class.java)
        context.startActivity(intent)
    }
}