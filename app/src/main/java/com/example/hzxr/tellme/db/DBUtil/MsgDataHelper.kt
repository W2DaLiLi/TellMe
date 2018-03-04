package com.example.hzxr.tellme.db.DBUtil

import com.example.hzxr.tellme.db.model.Msg
import io.objectbox.BoxStore

/**
 * Created by Hzxr on 2018/3/4.
 */
object MsgDataHelper {

    fun add(boxStore: BoxStore, data: Map<String, String?>) {
        val msgBox = boxStore.boxFor(Msg::class.java)

    }

    fun getAllChatMsgs(boxStore: BoxStore): List<Msg> {
        val msgBox = boxStore.boxFor(Msg::class.java)
        return msgBox.all
    }

    private fun mapToMsgObject(data: Map<String, String?>): Msg{
        val msg = Msg()
        return msg
    }
}