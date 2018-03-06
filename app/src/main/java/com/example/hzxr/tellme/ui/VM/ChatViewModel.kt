package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.databinding.ActivityChatBinding
import com.example.hzxr.tellme.db.DBUtil.MemberDataHelper
import com.example.hzxr.tellme.db.DBUtil.MsgDataHelper
import com.example.hzxr.tellme.db.model.Msg
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.ui.adapter.MessageRecyclerAdapter
import org.jivesoftware.smack.chat.Chat
import org.jivesoftware.smack.chat.ChatManager
import org.jxmpp.jid.EntityJid
import org.jxmpp.jid.impl.JidCreate
import org.jxmpp.jid.util.JidUtil

/**
 * Created by Hzxr on 2018/2/27.
 */
class ChatViewModel(activity: Activity, binding: ActivityChatBinding, targetName: String) : BaseViewModel<ActivityChatBinding>(activity, binding) {

    private val boxStore = (activity.application as TellMeApp).boxStore
    private val target = MemberDataHelper.queryMemberByName(boxStore, targetName)

    private var msgList = MsgDataHelper.queryMsgByUser(boxStore, targetName)

    private val chat : Chat

//    private val chatManager = ConnectManager.getChatManager()

    //    private val allChatMsg: List<Msg>
    private val layoutManager: LinearLayoutManager by lazy {
        binding.messagesRv.layoutManager as LinearLayoutManager
    }

    val adapter = MessageRecyclerAdapter(activity, msgList ?: mutableListOf())

    init {
        val chatManager = ChatManager.getInstanceFor(ConnectManager.getConnect())
        chat = chatManager.createChat(JidCreate.entityBareFrom(targetName))
    }

    val sendMessageOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            val message = binding.editMessage.text?: return@OnClickListener
            chat.sendMessage(message.toString())
            binding.editMessage.text = null
        }


    fun onResume() {

    }

    fun onPause() {

    }

    fun onDestroy() {

    }
}