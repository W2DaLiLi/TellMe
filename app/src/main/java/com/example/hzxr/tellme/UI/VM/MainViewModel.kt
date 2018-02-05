package com.example.hzxr.tellme.UI.VM

import android.app.Activity
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.Util.ActivitysUtil
import com.example.hzxr.tellme.databinding.ActivityMainBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDatehelper

/**
 * Created by Hzxr on 2018/1/20.
 */
class MainViewModel(activity: Activity, binding: ActivityMainBinding) : BaseViewModel<ActivityMainBinding>(activity, binding) {

    val testLoginActivityOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            Log.d("TAG", "test click button")
            ActivitysUtil.startActivityToLogin(activity)
        }

    val testRegisterActivityOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToRegister(activity)
        }

    val testDebugOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            val box = (activity.application as TellMeApp).boxStore
            val account = AccountDatehelper.queryAccountByUsername(box, "000")
            Log.d("TAG", account.toString())
        }

    val testHomeOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToHome(activity)
        }
}