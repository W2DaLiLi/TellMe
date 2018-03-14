package com.example.hzxr.tellme.ui.VM

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.Util.ToastUtil
import com.example.hzxr.tellme.databinding.ActivitySetFriendBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper
import com.example.hzxr.tellme.db.model.Member
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.service.FetchDataIntentService
import org.jivesoftware.smack.XMPPException
import org.jivesoftware.smack.packet.Presence
import org.jivesoftware.smack.roster.Roster
import org.jxmpp.jid.Jid
import org.jxmpp.jid.impl.JidCreate

/**
 * Created by Hzxr on 2018/3/13.
 */
class SetFriendViewModel(activity: Activity, binding: ActivitySetFriendBinding,private val targetName: String) : BaseViewModel<ActivitySetFriendBinding>(activity, binding) {

    private val TAG = "SetFriendViewModel"

    init {

    }

    var nickname: String? = null

    var teamName: String? = null

    val navigationOnClickListener: View.OnClickListener
        get() = View.OnClickListener { activity.onBackPressed() }

    val submitOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            Log.d(TAG, "submit add $targetName to friend")
            submitAddFriend(targetName)
        }

    private fun submitAddFriend(name: String) {
        Log.d(TAG, "the name: $name")
        Thread {
            val roster = Roster.getInstanceFor(ConnectManager.getConnect())
            val jid = JidCreate.bareFrom("$name@localhost")
            val tname = teamName ?: "friend"
            try {
                roster.createEntry(jid, null, arrayOf(tname))
                handler.sendEmptyMessage(1)
            } catch (e: XMPPException) {
                e.printStackTrace()
                handler.sendEmptyMessage(2)
            }
        }.start()
    }

    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                1 -> {
                    ToastUtil.showShort(activity, "添加成功")
                    val username = AccountDataHelper.currentAccount?.username?: return
                    FetchDataIntentService.startService(activity, username)
                }
                2 -> ToastUtil.showShort(activity, "添加失败")
            }
            activity.finish()
        }
    }
}