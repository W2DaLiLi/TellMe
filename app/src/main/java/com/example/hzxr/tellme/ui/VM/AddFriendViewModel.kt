package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.Util.TextWatcherHelper
import com.example.hzxr.tellme.databinding.ActivityAddFriendBinding
import com.example.hzxr.tellme.net.ApiClient
import com.example.hzxr.tellme.net.model.User
import com.example.hzxr.tellme.ui.adapter.UserRecyclerAdapter
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
//                val key = editable.toString()
//                userList?.filter { user ->
//                    user.username.contains(key)
//                }
//                adapter.notifyDataSetChanged()
                Log.d("TAG", userList.toString())
            }
        }

    init {
        ApiClient.getAllUserInServer()
        ApiClient.listener = {
            list ->
            adapter.userList = list
            adapter.notifyDataSetChanged()
        }
        binding.searchResultRv.layoutManager = LinearLayoutManager(activity)
        Log.d("TAG", userList.toString())
        adapter.userList = userList?: listOf()
        adapter.onItemClickListener = {
            position ->
            Log.d("TAG", "the position: " + position)
        }
    }
}