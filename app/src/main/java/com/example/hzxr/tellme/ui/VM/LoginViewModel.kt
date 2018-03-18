package com.example.hzxr.tellme.ui.VM

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.Util.ActivitysUtil
import com.example.hzxr.tellme.Util.SharePreferencesManager
import com.example.hzxr.tellme.Util.TextWatcherHelper
import com.example.hzxr.tellme.databinding.ActivityLoginBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.service.EventService
import com.example.hzxr.tellme.service.FetchDataIntentService
import com.example.hzxr.tellme.service.MessageService
import com.example.hzxr.tellme.ui.HomeActivity
import org.jivesoftware.smack.XMPPException

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

    init {
        if(SharePreferencesManager.hasUserInfo(activity)){
            val userInfo = SharePreferencesManager.getUserInfo(activity)
            val name = userInfo["username"]
            val pw = userInfo["password"]
            if (name != null && pw != null){
                login(name, pw)
            }
        }
    }

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

    val usernameOnEditorActionListener: TextView.OnEditorActionListener
        get() = TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (TextUtils.isEmpty(username)) {
                    usernameInputError = activity.getString(R.string.errorUsername)
                    return@OnEditorActionListener true
                }
            }
            false
        }

    val passwordOnEditorActionListener: TextView.OnEditorActionListener
        get() = TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (!checkValid()) return@OnEditorActionListener false
                login(username?: return@OnEditorActionListener false, password?: return@OnEditorActionListener false)
                return@OnEditorActionListener true
            }
            false
        }

    val onLoginButtonClickListener: View.OnClickListener
        get() = View.OnClickListener {
            if (!checkValid()) return@OnClickListener
            login(username?: return@OnClickListener, password?: return@OnClickListener)
        }

    private fun checkValid(): Boolean {
        usernameInputError = if (TextUtils.isEmpty(username)) activity.getString(R.string.errorUsername) else null
        passwordInputError = if (TextUtils.isEmpty(password)) activity.getString(R.string.errorPassword) else null
        notifyChange()
        return null == usernameInputError && null == passwordInputError
    }

    private fun login(username: String, password: String) {
        Log.d("TAG", "username:" + username + " password:" + password + " remember:" + rememberPW + " auto:" + autoLogin)
        Thread {
            try {
                val connect = ConnectManager.getConnect() ?: return@Thread
                connect.login(username, password)
                handler.sendEmptyMessage(1)
            } catch (e: XMPPException) {
                Log.d("TAG", e.toString())
                handler.sendEmptyMessage(2)
            }
        }.start()
    }

    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                1 -> {
                    Toast.makeText(activity, "登陆成功", Toast.LENGTH_SHORT).show()
                    //todo:goto homeActivity
                    startEventService()
                    startMessageService()
                    startHomeActivityAndFetchDataService()
                    if (autoLogin == true){
                        SharePreferencesManager.saveAccountInfo(activity, username?: return, password?: return)
                    }
                }
                2 -> {
                    Toast.makeText(activity, "登陆失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startEventService() {
        val intent = Intent(activity, EventService::class.java)
        activity.startService(intent)
    }

    private fun startMessageService() {
        val intent = Intent(activity, MessageService::class.java)
        activity.startService(intent)
    }

    private fun startHomeActivityAndFetchDataService() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        FetchDataIntentService.startService(activity, username ?: return)
        activity.finish()
    }

    val debugEnter: View.OnClickListener
        get() = View.OnClickListener { ActivitysUtil.startActivityToMainDebug(activity) }

    val registerEnter: View.OnClickListener
        get() = View.OnClickListener { ActivitysUtil.startActivityToRegister(activity) }
}