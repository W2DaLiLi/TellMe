package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.databinding.ActivityChatBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper
import com.example.hzxr.tellme.db.DBUtil.MemberDataHelper
import com.example.hzxr.tellme.db.DBUtil.MsgDataHelper
import com.example.hzxr.tellme.db.model.Msg
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.ui.adapter.MessageRecyclerAdapter
import com.example.hzxr.tellme.ui.msgType.Constants
import io.objectbox.android.AndroidScheduler
import io.objectbox.query.Query
import io.objectbox.reactive.DataSubscription
import org.jivesoftware.smack.chat.Chat
import org.jivesoftware.smack.chat.ChatManager
import org.jivesoftware.smack.packet.Message
import org.jxmpp.jid.EntityJid
import org.jxmpp.jid.impl.JidCreate
import org.jxmpp.jid.util.JidUtil

/**
 * Created by Hzxr on 2018/2/27.
 */
class ChatViewModel(activity: Activity, binding: ActivityChatBinding, val targetName: String) : BaseViewModel<ActivityChatBinding>(activity, binding) {

    private val boxStore = (activity.application as TellMeApp).boxStore
    private val target = MemberDataHelper.queryMemberByName(boxStore, targetName)

    private var msgList = MsgDataHelper.queryMsgByUser(boxStore, targetName)

    private val chat: Chat

//    private val chatManager = ConnectManager.getChatManager()

    //    private val allChatMsg: List<Msg>
    private val layoutManager: LinearLayoutManager by lazy {
        binding.messagesRv.layoutManager as LinearLayoutManager
    }

    val adapter = MessageRecyclerAdapter(activity, msgList ?: mutableListOf())

    private val subscription: DataSubscription

    init {
        val chatManager = ChatManager.getInstanceFor(ConnectManager.getConnect())
        chat = chatManager.createChat(JidCreate.entityBareFrom(targetName))
        val msgQuery = MsgDataHelper.getQueryByUser(boxStore, targetName)
        subscription = msgQuery!!.subscribe().on(AndroidScheduler.mainThread()).observer { list ->
            adapter.msgList = list
            adapter.notifyDataSetChanged()
        }
    }

    val sendMessageOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            val msg = buildMsg(binding.editMessage.text.toString()) ?: return@OnClickListener
            chat.sendMessage(msg.content)
            binding.editMessage.text = null
            MsgDataHelper.add(boxStore, msg)
            adapter.notifyDataSetChanged()
        }

    private fun buildMsg(content: String?): Msg? {
        val msg = Msg()
        if (content.isNullOrEmpty()) return null
        msg.content = content
        msg.type = Constants.MESSAGE_TYPE_SELF_TEXT
        msg.from = AccountDataHelper.currentAccount?.username ?: return null
        msg.to = targetName
        msg.subject = null
        return msg
    }


    fun onResume() {

    }

    fun onPause() {

    }

    fun onDestroy() {
        if (!subscription.isCanceled)
            subscription.cancel()
    }
}