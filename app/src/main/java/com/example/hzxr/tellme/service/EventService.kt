package com.example.hzxr.tellme.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by Hzxr on 2018/2/20.
 */
class EventService: Service() {
    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}