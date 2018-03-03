package com.example.hzxr.tellme.ui.msgType

/**
 * Created by Hzxr on 2018/3/3.
 */
interface MsgType {
    fun type(msgTypeFactory: MsgTypeFactory): Int
}