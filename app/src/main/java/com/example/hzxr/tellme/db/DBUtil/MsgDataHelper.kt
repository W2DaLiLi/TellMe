package com.example.hzxr.tellme.db.DBUtil

import com.example.hzxr.tellme.db.model.Msg
import com.example.hzxr.tellme.db.model.Msg_
import com.example.hzxr.tellme.ui.msgType.Constants
import io.objectbox.BoxStore
import io.objectbox.query.Query
import org.jivesoftware.smack.packet.Message

/**
 * Created by Hzxr on 2018/3/4.
 */
object MsgDataHelper {

    fun add(boxStore: BoxStore, data: Map<String, String?>) {
        val msgBox = boxStore.boxFor(Msg::class.java)
        msgBox.put(mapToMsgObject(data))
    }

    fun add(boxStore: BoxStore, message: Message) {
        val msgBox = boxStore.boxFor(Msg::class.java)
        msgBox.put(messageToMsgObject(message))
    }

    fun add(boxStore: BoxStore, msg: Msg) {
        val msgBox = boxStore.boxFor(Msg::class.java)
        msgBox.put(msg)
    }

    fun delete(boxStore: BoxStore, username: String) {
        val msgBox = boxStore.boxFor(Msg::class.java)
        msgBox.remove(queryMsgByUser(boxStore, username))
    }

    fun queryMsgByUser(boxStore: BoxStore, username: String): List<Msg>? {
        val msgBox = boxStore.boxFor(Msg::class.java)
        return msgBox.query().equal(Msg_.to, username).
                or().
                equal(Msg_.from, username).
                build().
                find()
    }

    fun getQueryByUser(boxStore: BoxStore, username: String): Query<Msg>? {
        val msgBox = boxStore.boxFor(Msg::class.java)
        val current = AccountDataHelper.currentAccount?.username?: return null
        return msgBox.query().
                contains(Msg_.to, username).
                or().
                contains(Msg_.from, username).
                build()
    }

    fun getAllChatMsgs(boxStore: BoxStore): List<Msg> {
        val msgBox = boxStore.boxFor(Msg::class.java)
        return msgBox.all
    }

    fun removeAllMsg(boxStore: BoxStore) {
        val msgBox = boxStore.boxFor(Msg::class.java)
        msgBox.removeAll()
    }

    private fun mapToMsgObject(data: Map<String, String?>): Msg{
        val msg = Msg()
        val current = AccountDataHelper.currentAccount?.username?: return msg
        msg.to = data["to"] as String
        msg.from = data["from"] as String
        msg.content = data["content"]
        msg.subject = data["subject"]
        msg.type = when("$current@localhost") {
            msg.from -> Constants.MESSAGE_TYPE_SELF_TEXT
            msg.to -> Constants.MESSAGE_TYPE_OTHER_TEXT
            else -> throw Exception("unknown msg type")
        }
        return msg
    }

    fun messageToMsgObject(message: Message): Msg {
        val msg = Msg()
        val current = AccountDataHelper.currentAccount?.username?: return msg
        msg.to = message.to.asBareJid().toString()
        msg.from = message.from.asBareJid().toString()
        msg.subject = message.subject
        msg.content = message.body
        msg.type = when("$current@localhost") {
            msg.from -> Constants.MESSAGE_TYPE_SELF_TEXT
            msg.to -> Constants.MESSAGE_TYPE_OTHER_TEXT
            else -> throw Exception("unknown msg type")
        }
        return msg
    }
}