package com.example.hzxr.tellme.db.DBUtil

import com.example.hzxr.tellme.db.model.Msg
import com.example.hzxr.tellme.db.model.Msg_
import com.example.hzxr.tellme.ui.msgType.Constants
import io.objectbox.BoxStore

/**
 * Created by Hzxr on 2018/3/4.
 */
object MsgDataHelper {

    fun add(boxStore: BoxStore, data: Map<String, String?>) {
        val msgBox = boxStore.boxFor(Msg::class.java)
        msgBox.put(mapToMsgObject(data))
    }

    fun delete(boxStore: BoxStore, username: String) {
        val msgBox = boxStore.boxFor(Msg::class.java)
        msgBox.remove(queryMsgByUser(boxStore, username))
    }

    fun queryMsgByUser(boxStore: BoxStore, username: String): List<Msg> {
        val msgBox = boxStore.boxFor(Msg::class.java)
        return msgBox.query().equal(Msg_.to, username).
                or().
                equal(Msg_.from, username).
                build().
                find()
    }

    fun getAllChatMsgs(boxStore: BoxStore): List<Msg> {
        val msgBox = boxStore.boxFor(Msg::class.java)
        return msgBox.all
    }

    private fun mapToMsgObject(data: Map<String, String?>): Msg{
        val msg = Msg()
        val current = AccountDataHelper.currentAccount?.username?: return msg
        msg.to = data["to"] as String
        msg.from = data["from"] as String
        msg.content = data["content"]
        msg.subject = data["subject"]
        msg.type = when(current) {
            msg.from -> Constants.MESSAGE_TYPE_SELF_TEXT
            msg.to -> Constants.MESSAGE_TYPE_OTHER_TEXT
            else -> throw Exception("unknown msg type")
        }
        return msg
    }
}