package com.example.hzxr.tellme.ui.VM

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.os.Handler
import android.os.Message
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.Util.TextWatcherHelper
import com.example.hzxr.tellme.Util.ToastUtil
import com.example.hzxr.tellme.databinding.ActivityAddFriendBinding
import com.example.hzxr.tellme.net.ApiClient
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.net.model.User
import com.example.hzxr.tellme.ui.adapter.UserRecyclerAdapter
import org.jivesoftware.smack.XMPPException
import org.jivesoftware.smack.roster.Roster
import org.jxmpp.jid.BareJid
import org.jxmpp.jid.impl.JidCreate
import kotlin.math.log

/**
 * Created by Hzxr on 2018/2/12.
 */
class AddFriendViewModel(activity: Activity, binding: ActivityAddFriendBinding) : BaseViewModel<ActivityAddFriendBinding>(activity, binding) {

    private var userList: List<User>? = null

    var searchText: String? = null

    val adapter = UserRecyclerAdapter(activity)

    val navigationOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            activity.onBackPressed()
        }

    val searchTextWatcher: TextWatcher
        get() = object : TextWatcherHelper() {
            override fun afterTextChanged(editable: Editable?) {
                //TODO:update recyclerview
                Log.d("TAG", editable.toString())
                val key = editable.toString()
                val list = userList?.filter { user ->
                    user.username.contains(key)
                }
                adapter.userList = list ?: return
                adapter.notifyDataSetChanged()
                Log.d("TAG", userList.toString())
            }
        }

    init {
        ApiClient.getAllUserInServer()?.subscribe { users ->
            adapter.userList = users.getUserList()
            userList = users.getUserList()
        }
        binding.searchResultRv.layoutManager = LinearLayoutManager(activity)
        Log.d("TAG", userList.toString())
        //这里这样拿值感觉会重复引用有问题，标注一下
        adapter.onItemClickListener = { position ->
            //todo:alterDialog
            val list = adapter.userList
            val name = list[position].username
            AlertDialog.Builder(activity).setTitle("好友添加提示")
                    .setMessage("是否添加 " + name + "为好友？")
                    .setPositiveButton("确定", { _, _ ->
                        submitAddFriend(name)
                    })
                    .setNegativeButton("取消", null)
                    .create()
                    .show()
        }
    }

    private fun submitAddFriend(name: String) {
        Log.d("TAG", "the name: " + name)
        Thread {
            val roster = Roster.getInstanceFor(ConnectManager.getConnect())
            val jid = JidCreate.bareFrom(name + "@localhost")
            try {
                roster.createEntry(jid, null, arrayOf("friend"))
                handler.sendEmptyMessage(1)
            } catch (e: XMPPException) {
                e.printStackTrace()
                handler.sendEmptyMessage(2)
            }
        }.start()
    }

    @SuppressLint("HandlerLeak")
    private val handler = object : Handler(){
        override fun handleMessage(msg: Message?) {
            when(msg?.what) {
                1 -> ToastUtil.showShort(activity, "添加成功")
                2 -> ToastUtil.showShort(activity, "添加失败")
            }
        }
    }
}