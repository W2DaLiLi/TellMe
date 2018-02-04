package com.example.hzxr.tellme.net

import android.util.Log
import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.XMPPException
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import java.util.logging.Logger

/**
 * Created by Hzxr on 2018/1/20.
 */
object ConnectManager {

    private const val SERVER_PORT = 5222
    private const val SERVER_HOST = "192.168.199.159"
    private const val SERVER_DOMAIN = "localhost"

    @Volatile
    private var connect: AbstractXMPPConnection? = null

    private fun initConnection() {
        Log.d("TAG", "initConnection")
        try {
            val config = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain(SERVER_DOMAIN)
                    .setHost(SERVER_HOST)
                    .setPort(SERVER_PORT)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .build()
            if (connect == null) {
                connect = XMPPTCPConnection(config)
            }
            connect?.connect()
            Log.d("TAG", "连接成功")
        } catch (e: XMPPException) {
            Log.d("TAG-Exception", e.toString())
        }
    }

    fun getConnect(): XMPPTCPConnection? {
        Log.d("TAG", connect.toString())
        if (connect == null) {
            initConnection()
        }
        return connect as XMPPTCPConnection?
    }

    fun disConnect() {
        if (connect != null) {
            connect?.disconnect()
            connect = null
        }
    }
}