package com.example.hzxr.tellme.UI.VM

import android.app.Activity
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.Util.ActivitysUtil
import com.example.hzxr.tellme.databinding.ActivityMainBinding

/**
 * Created by Hzxr on 2018/1/20.
 */
class MainViewModel(activity: Activity, binding: ActivityMainBinding) : BaseViewModel<ActivityMainBinding>(activity, binding) {

    val testLoginActivityOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            Log.d("TAG", "test click button")
            ActivitysUtil.startActivityToLogin(activity)
        }
}