package com.example.hzxr.tellme.ui.msgType

import android.view.ViewGroup
import com.example.hzxr.tellme.db.model.Msg

/**
 * Created by Hzxr on 2018/3/3.
 */
interface MsgTypeFactory {
    fun type(msg: Msg): Int

    fun createViewHolder(type: Int, parent: ViewGroup?)
}