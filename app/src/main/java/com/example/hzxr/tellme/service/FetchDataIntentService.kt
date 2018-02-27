package com.example.hzxr.tellme.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper
import com.example.hzxr.tellme.db.DBUtil.GroupDataHelper
import com.example.hzxr.tellme.db.DBUtil.MemberDataHelper
import com.example.hzxr.tellme.db.model.Group
import com.example.hzxr.tellme.db.model.Member
import com.example.hzxr.tellme.net.ConnectManager
import io.objectbox.BoxStore
import io.objectbox.relation.RelationInfo
import io.objectbox.relation.ToMany

/**
 * Created by Hzxr on 2018/2/25.
 */
class FetchDataIntentService : IntentService("FetchData") {

//    private val boxStore = (application as TellMeApp).boxStore

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "FetchDataIntentService onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "FetchDataIntentService onDestroy")
    }

    companion object {
        fun startService(context: Context, username: String) {
            val intent = Intent(context, FetchDataIntentService::class.java)
            intent.putExtra("username", username)
            context.startService(intent)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        val username = intent?.getStringExtra("username") ?: return
        val boxStore = (application as TellMeApp).boxStore
        fetchAndLoadAccountData(boxStore, username)
        fetchAndLoadGroupData(boxStore)
        fetchAndLoadMembersData(boxStore)
    }

    private fun fetchAndLoadAccountData(boxStore: BoxStore, username: String) {
        if (AccountDataHelper.queryAccountByUsername(boxStore, username) == null) {
            val accountManager = ConnectManager.getAccountManager() ?: return
            val name = accountManager.getAccountAttribute("name")
            val email = accountManager.getAccountAttribute("email")
            val accountMap = mutableMapOf("username" to username,
                    "nickname" to name,
                    "email" to email,
                    "role" to "user",
                    "friends" to null).toMap()
            AccountDataHelper.add(boxStore, accountMap)
        }
        //目前只允许在登陆处设置当前用户，后续如果有切换账号功能可能会再做修改
        AccountDataHelper.setCurrentAccountByName(boxStore, username)
    }

    //这里member 和group的多对多关系
    private fun fetchAndLoadMembersData(boxStore: BoxStore) {
        val roster = ConnectManager.getRoster() ?: return
        val entries = roster.entries
        Log.d("TAG", "entry count: " + entries.size)
        if (entries.isEmpty()) return
        for (item in entries) {
            val data = mutableMapOf("username" to item.jid.toString(),
                    "nickname" to item.name,
                    "parentId" to item.groups.map { rosterGroup ->
                        GroupDataHelper.queryGroupByName(boxStore, rosterGroup.name)
                    }.toList()).toMap()
            Log.d("TAG", data.toString())
            MemberDataHelper.add(boxStore, data)
        }
    }

    private fun fetchAndLoadGroupData(boxStore: BoxStore) {
        val roster = ConnectManager.getRoster() ?: return
        val groups = roster.groups
        Log.d("TAG", "groups:" + groups.size)
        if (groups.isEmpty()) return
        for (item in groups) {
            val data = mutableMapOf("name" to item.name,
                    "description" to null,
                    "admin" to null).toMap()
            Log.d("TAG", data.toString())
            GroupDataHelper.add(boxStore, data)
        }
    }
}