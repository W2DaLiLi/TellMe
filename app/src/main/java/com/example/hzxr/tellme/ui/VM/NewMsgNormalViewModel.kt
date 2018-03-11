package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.databinding.ViewDataBinding
import android.util.Log

/**
 * Created by Hzxr on 2018/3/3.
 */
class NewMsgNormalViewModel(activity: Activity, binding: ViewDataBinding) : NewMsgBaseViewModel(activity, binding) {

    //按照原来的方法拿一直都是空数据，目前先这样，后期再优化
    val msgText : String by lazy {
        msg.content?: ""
    }
}