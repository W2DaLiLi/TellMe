package com.example.hzxr.tellme.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.WindowManager
import com.example.hzxr.tellme.net.ConnectManager

/**
 * Created by Hzxr on 2018/3/6.
 */
class MessageService : Service(){
    
    private val chatManger = ConnectManager.getChatManager()
    
    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG","MessageService onCreate")
        if (chatManger != null) {
            chatManger.addIncomingListener { from, message, chat ->
                Log.d("TAG", "Incoming: " + from.toString() + " Message: " + message.toString())
                chat.send("Hello")
                val dialog = AlertDialog.Builder(this)
                        .setTitle("message")
                        .setMessage("Incoming: " + from.toString() + " Message: " + message.toString())
                        .create()
                dialog.window.setType(WindowManager.LayoutParams.TYPE_TOAST)
            }
            chatManger.addOutgoingListener { to, message, chat ->
                Log.d("TAG", "Outgoing" + to.toString() + " Message " + message.toString())
            }
        } else
            return
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "MessageService onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_REDELIVER_INTENT
    }
}