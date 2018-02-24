package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.Util.TextWatcherHelper
import com.example.hzxr.tellme.databinding.ActivityRegisterBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper
import com.example.hzxr.tellme.net.ConnectManager
import org.jivesoftware.smack.XMPPException
import org.jivesoftware.smackx.iqregister.AccountManager
import org.jxmpp.jid.parts.Localpart

/**
 * Created by Hzxr on 2018/2/1.
 */
class RegisterViewModel(activity: Activity, binding: ActivityRegisterBinding) : BaseViewModel<ActivityRegisterBinding>(activity, binding) {

    var username: String? = null
    var password: String? = null
    var email: String? = null

    var usernameInputError: String? = null
    var passwordInputError: String? = null
    var emailInputError: String? = null

    val usernameTextWatcher: TextWatcher
        get() = object : TextWatcherHelper() {
            override fun afterTextChanged(p0: Editable?) {
                super.afterTextChanged(p0)
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

    val emailTextWatcher: TextWatcher
        get() = object : TextWatcherHelper() {
            override fun afterTextChanged(p0: Editable?) {
                super.afterTextChanged(p0)
                emailInputError = if (TextUtils.isEmpty(email)) activity.getString(R.string.errorEmail) else null
                notifyChange()
            }
        }

    val onRegisterOnClickListener: View.OnClickListener
        get() = View.OnClickListener { register() }

    private fun checkValid(): Boolean {
        usernameInputError = if (TextUtils.isEmpty(username)) activity.getString(R.string.errorUsername) else null
        passwordInputError = if (TextUtils.isEmpty(password)) activity.getString(R.string.errorPassword) else null
        emailInputError = if (TextUtils.isEmpty(email)) activity.getString(R.string.errorEmail) else null
        notifyChange()
        return null == usernameInputError && null == passwordInputError && null == emailInputError
    }


    private fun register() {
        if (!checkValid()) return
        Log.d("TAG", "Register:" + username + password + email)
        Thread {
            try {
                val connect = ConnectManager.getConnect() ?: return@Thread
                val accountManager = AccountManager.getInstance(connect)
                accountManager.sensitiveOperationOverInsecureConnection(true)
                val localPart = Localpart.from(username)
                val email = email ?: return@Thread
                val map = mutableMapOf("email" to email)
                accountManager.createAccount(localPart, password, map)
                saveAccount()
            } catch (e: XMPPException) {
                Log.d("TAG", e.toString())
            }
        }.start()
    }
//这里字段的判空设计的有点问题，后面要改进
    private fun saveAccount() {
        val data = mutableMapOf("username" to username!!,
                "email" to email!!,
                "nickname" to null,
                "role" to "user",
                "friends" to null).toMap()
        val box = (activity.application as TellMeApp).boxStore
        AccountDataHelper.add(box, data)
    }
}