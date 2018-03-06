package com.example.hzxr.tellme.db.model

import com.example.hzxr.tellme.ui.msgType.MsgType
import com.example.hzxr.tellme.ui.msgType.MsgTypeFactory
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Hzxr on 2018/1/20.
 */
@Entity
data class Msg(
        @Id
        var id: Long = 0,
        var type: String,
        var to: String,
        var from: String,
        var content: String? = null,
        var subject: String? = null
) : MsgType{
    constructor() : this(0, "", "", "", null, "")

    override fun type(msgTypeFactory: MsgTypeFactory): Int {
        return msgTypeFactory.type(this)
    }

    override fun toString(): String {
        return "id: $id type: $type to: $to from: $from content: $content subject: $subject"
    }
}