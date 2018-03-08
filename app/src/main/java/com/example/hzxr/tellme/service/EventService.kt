package com.example.hzxr.tellme.service

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.Util.ToastUtil
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.ui.HomeActivity
import org.jivesoftware.smack.StanzaListener
import org.jivesoftware.smack.filter.AndFilter
import org.jivesoftware.smack.filter.StanzaTypeFilter
import org.jivesoftware.smack.packet.Presence

/**
 * Created by Hzxr on 2018/2/20.
 */
class EventService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "EventService onCreate")
        val filter = AndFilter(StanzaTypeFilter(Presence::class.java))//登陆后生效，未登录状态会抛出XmppException
        ConnectManager.getConnect()?.addAsyncStanzaListener(packetListener, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private val packetListener = StanzaListener { packet ->
        if (packet is Presence) {
            val fromId = packet.from
            when (packet.type) {
                Presence.Type.subscribe -> {
                    //我好友申请
                    Log.d("TAG", "我好友申请")
                }
                Presence.Type.subscribed -> {
                    //对方同意订阅
                    Log.d("TAG", "对方同意订阅")
                }
                Presence.Type.unsubscribed -> {
                    //对方拒绝订阅
                    Log.d("TAG", "对方拒绝订阅")
                }
                Presence.Type.unsubscribe -> {
                    //我取消订阅
                    Log.d("TAG", "我取消订阅")
                }
                Presence.Type.unavailable -> {
                    //离线
                    Log.d("TAG", fromId.toString() + "离线")
                    val activity = (application as TellMeApp).activityLifecycleCallbacks.currentActivity()
                    if (activity == null) {
                        Log.d("TAG", "isEmpty")
                        return@StanzaListener
                    }
                    activity.runOnUiThread {
                        ToastUtil.showShort(activity, fromId.toString() + "离线")
                    }
                }
                Presence.Type.available -> {
                    //上线
                    Log.d("TAG", fromId.toString() + "上线")
                    val activity = (application as TellMeApp).activityLifecycleCallbacks.currentActivity()
                    if (activity == null) {
                        Log.d("TAG", "isEmpty")
                        return@StanzaListener
                    }
                    activity.runOnUiThread {
                        ToastUtil.showShort(activity, fromId.toString() + "上线")
                    }
                }
                else -> throw RuntimeException("receive unknown packet")
            }
        }
    }
}