package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.hzxr.tellme.TellMeApp
import com.example.hzxr.tellme.databinding.FragmentFriendsBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper
import com.example.hzxr.tellme.db.DBUtil.MemberDataHelper
import com.example.hzxr.tellme.ui.adapter.MemberRecyclerAdapter
import com.example.hzxr.tellme.ui.adapter.UserRecyclerAdapter

/**
 * Created by Hzxr on 2018/2/5.
 */
class FriendsViewModel(activity: Activity, binding: FragmentFriendsBinding) : BaseViewModel<FragmentFriendsBinding>(activity, binding) {

    private val boxStore = (activity.application as TellMeApp).boxStore

    val adapter = MemberRecyclerAdapter(activity)

    init {
        //这里会产生重复数据，是因为currentAccount不是从数据库中取出的，所以每次重新登陆，都会重新拉取，重复赋值，后面用Rxjava结合ObjectBox做更新后自动刷新
        adapter.members = AccountDataHelper.currentAccount?.friends?: listOf()
        binding.friendsRv.layoutManager = LinearLayoutManager(activity)
        adapter.onItemOnClickListener = {position ->
            Log.d("TAG", position.toString())
        }
    }
}