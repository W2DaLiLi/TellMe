package com.example.hzxr.tellme.net

import android.util.Log
import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.XMPPException
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smackx.iqregister.AccountManager
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

    private var roster: Roster? = null

    private var accountManager: AccountManager? = null

    private var chatManager: ChatManager? = null

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

    @Synchronized
    fun getConnect(): XMPPTCPConnection? {
        Log.d("TAG", connect.toString())
        if (connect == null) {
            initConnection()
        }
        return connect as XMPPTCPConnection?
    }

    /**
     * 不要在除了ConnectManger以外的地方去构造需要使用connect的对象
     */
    @Synchronized
    fun getRoster(): Roster? {
        if (connect != null) {
            if (roster == null)
                roster = Roster.getInstanceFor(connect)
        }
        return roster
    }

    @Synchronized
    fun getAccountManager(): AccountManager? {
        if (connect != null) {
            if (accountManager == null)
                accountManager = AccountManager.getInstance(connect)
        }
        return accountManager
    }

    @Synchronized
    fun getChatManager(): ChatManager? {
        if (connect != null) {
            if (chatManager == null)
                chatManager = ChatManager.getInstanceFor(connect)
        }
        return chatManager
    }

    fun disConnect() {
        if (connect != null) {
            connect?.disconnect()
            connect = null
        }
    }
}