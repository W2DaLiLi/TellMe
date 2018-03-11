package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.databinding.BaseObservable
import android.databinding.ViewDataBinding
import com.example.hzxr.tellme.db.model.Msg

/**
 * Created by Hzxr on 2018/3/3.
 */
abstract class NewMsgBaseViewModel(protected val activity: Activity, protected val binding: ViewDataBinding) : BaseObservable() {

    private var _msg: Msg? = null

    open fun setMsg(msg: Msg) {
        this._msg = msg
    }

    val msg: Msg
        get() = _msg!!

    val senderName: String
        get() = msg.from
}