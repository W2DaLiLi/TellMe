package com.example.hzxr.tellme.net

import android.content.Context
import com.example.hzxr.tellme.TellMeApp
import io.objectbox.BoxStore

/**
 * Created by Hzxr on 2018/1/20.
 */
class EventHandler(context: Context) {

    private val connect = ConnectManager.getConnect()
    private val boxStore = ((context.applicationContext) as TellMeApp).boxStore

    fun handle(event: Event) {
        when (event.type) {
            EventType.USER_LOGIN -> handleUserLogin()
            EventType.USER_LOGOUT -> handleUserLogout()
            EventType.USER_REGISTER -> handleUserRegister()
            EventType.SEND_MESSAGE -> handleSendMessage()
            EventType.RECV_MESSAGE -> handleRecvMessage()
        }
    }

    private fun handleUserLogin() {}

    private fun handleUserRegister() {}

    private fun handleUserLogout() {}

    private fun handleSendMessage() {}

    private fun handleRecvMessage() {}
}