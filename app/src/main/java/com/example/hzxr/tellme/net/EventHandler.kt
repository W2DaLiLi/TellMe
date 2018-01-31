package com.example.hzxr.tellme.net

import android.content.Context
import com.example.hzxr.tellme.TellMeApp
import io.objectbox.BoxStore
import org.jivesoftware.smack.tcp.XMPPTCPConnection

/**
 * Created by Hzxr on 2018/1/20.
 */
object EventHandler {

    fun handleUserLogin(boxStore: BoxStore, connect: XMPPTCPConnection) {}

    fun handleUserRegister(boxStore: BoxStore, connect: XMPPTCPConnection) {}

    fun handleUserLogout(boxStore: BoxStore, connect: XMPPTCPConnection) {}

    fun handleSendMessage(boxStore: BoxStore, connect: XMPPTCPConnection) {}

    fun handleRecvMessage(boxStore: BoxStore, connect: XMPPTCPConnection) {}
}