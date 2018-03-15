package com.example.hzxr.tellme.Util

import android.content.Context
import android.text.TextUtils
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper

/**
 * Created by Hzxr on 2018/3/15.
 */
object SharePreferencesManager {

    fun saveAccoutInfo(context: Context, username: String, password: String) {
        val sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("USERNAME", username)
        editor.putString("PASSWORD", password)
        editor.apply()
    }

    fun getUserInfo(context: Context): Map<String, String> {
        val sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        return mapOf("username" to sp.getString("USERNAME", ""), "password" to sp.getString("PASSWORD", ""))
    }

    fun hasUserInfo(context: Context): Boolean {
        val data = getUserInfo(context)
        if (data.isNotEmpty()) {
            if (!(TextUtils.isEmpty(data["username"])) && !(TextUtils.isEmpty(data["password"])))
                return true
        }
        return false
    }
}