package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.databinding.ViewDataBinding
import android.util.Log

/**
 * Created by Hzxr on 2018/3/3.
 */
class NewMsgNormalViewModel(activity: Activity, binding: ViewDataBinding) : NewMsgBaseViewModel(activity, binding) {

    val msgText = msg.content
}