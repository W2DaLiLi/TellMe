package com.example.hzxr.tellme.UI.VM

import android.app.Activity
import android.databinding.BaseObservable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.Util.TextWatcherHelper
import com.example.hzxr.tellme.databinding.ActivityLoginBinding

/**
 * Created by Hzxr on 2018/1/20.
 */
class LoginViewModel(activity: Activity, binding: ActivityLoginBinding) : BaseViewModel<ActivityLoginBinding>(activity, binding) {

    var username: String? = null
    var password: String? = null

    var rememberPW: Boolean = false
    var autoLogin: Boolean = false

    var usernameInputError: String? = null
    var passwordInputError: String? = null

    val usernameTextWatcher: TextWatcher
        get() = object : TextWatcherHelper() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                usernameInputError = if (TextUtils.isEmpty(username)) activity.getString(R.string.errorUsername) else null
                notifyChange()
            }
        }

    val passwordTextWatcher: TextWatcher
        get() = object : TextWatcherHelper() {
            override fun afterTextChanged(p0: Editable?) {
                super.afterTextChanged(p0)
                passwordInputError = if (TextUtils.isEmpty(password)) activity.getString(R.string.errorPassword) else null
                notifyChange()
            }
        }

    val onLoginButtonClickListener: View.OnClickListener
        get() = View.OnClickListener { login() }

    private fun checkValid(): Boolean {
        usernameInputError = if (TextUtils.isEmpty(username)) activity.getString(R.string.errorUsername) else null
        passwordInputError = if (TextUtils.isEmpty(password)) activity.getString(R.string.errorPassword) else null
        notifyChange()
        return null == usernameInputError && null == passwordInputError
    }

    private fun login() {
        if (!checkValid()) return
        Log.d("TAG", "username:" + username + " password:" + password + " remember:" + rememberPW + " auto:" + autoLogin)
    }
}