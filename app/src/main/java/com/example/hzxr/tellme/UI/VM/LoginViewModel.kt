package com.example.hzxr.tellme.UI.VM

import android.app.Activity
import android.databinding.BaseObservable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.hzxr.tellme.databinding.ActivityLoginBinding

/**
 * Created by Hzxr on 2018/1/20.
 */
class LoginViewModel(activity: Activity, binding: ActivityLoginBinding) : BaseViewModel<ActivityLoginBinding>(activity, binding) {

    var username: String? = null
    var password: String? = null

    var rememberPW: Boolean = false
    var autoLogin: Boolean = false

    val onLoginButtonClickListener: View.OnClickListener
        get() = View.OnClickListener { login() }

    private fun login() {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) return
        Log.d("TAG","username:" + username + " password:" + password + " remember:" + rememberPW + " auto:" + autoLogin)
    }
}