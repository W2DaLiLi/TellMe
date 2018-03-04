package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.databinding.ActivityChatBinding
import com.example.hzxr.tellme.db.DBUtil.MemberDataHelper

/**
 * Created by Hzxr on 2018/2/27.
 */
class ChatViewModel(activity: Activity, binding: ActivityChatBinding, targetName: String) : BaseViewModel<ActivityChatBinding>(activity, binding) {

    private val boxStore = (activity.application as TellMeApp).boxStore
    private val target = MemberDataHelper.queryMemberByName(boxStore,targetName)

    fun onResume() {

    }

    fun onPause() {

    }

    fun onDestroy() {

    }
}