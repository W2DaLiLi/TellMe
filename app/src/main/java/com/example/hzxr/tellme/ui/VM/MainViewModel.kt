package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.Util.ActivitysUtil
import com.example.hzxr.tellme.databinding.ActivityMainBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper
import com.example.hzxr.tellme.db.DBUtil.GroupDataHelper
import com.example.hzxr.tellme.db.DBUtil.MemberDataHelper
import com.example.hzxr.tellme.db.model.*
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.ui.ChatActivity
import io.objectbox.internal.ToManyGetter
import org.jivesoftware.smackx.offline.OfflineMessageManager

/**
 * Created by Hzxr on 2018/1/20.
 */
class MainViewModel(activity: Activity, binding: ActivityMainBinding) : BaseViewModel<ActivityMainBinding>(activity, binding) {

    val testLoginActivityOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            Log.d("TAG", "test click button")
            ActivitysUtil.startActivityToLogin(activity)
        }

    val testRegisterActivityOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToRegister(activity)
        }

    val testDebugOnClickListener: View.OnClickListener
        get() = View.OnClickListener {

//            val box = (activity.application as TellMeApp).boxStore
//            val accountBox = box.boxFor(Account::class.java)
//            val list = accountBox.query().equal(Account_.username, "123").build().findFirst()?.friends
//            Log.d("TAG", list?.toList().toString())
            val messageManager = OfflineMessageManager(ConnectManager.getConnect())
            for (item in messageManager.messages)
                Log.d("TAG", "body: " + item.body + "subject" + item.subject )
//            val intent = Intent(activity, ChatActivity::class.java)
//            activity.startActivity(intent)
        }

    val testHomeOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToHome(activity)
        }

    val testAddFriendOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToAddFriend(activity)
        }

    val testCleanDBOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            val boxStore = (activity.application as TellMeApp).boxStore
            AccountDataHelper.removeAll(boxStore)
            GroupDataHelper.removeAll(boxStore)
            MemberDataHelper.removeAll(boxStore)
        }
}