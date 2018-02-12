package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.Util.TextWatcherHelper
import com.example.hzxr.tellme.databinding.ActivityAddFriendBinding
import com.example.hzxr.tellme.ui.adapter.UserRecyclerAdapter
import kotlin.math.log

/**
 * Created by Hzxr on 2018/2/12.
 */
class AddFriendViewModel(activity: Activity, binding: ActivityAddFriendBinding) : BaseViewModel<ActivityAddFriendBinding>(activity, binding) {

    var searchText: String? = null

    val adapter = UserRecyclerAdapter(activity, arrayListOf("123", "2356", "5345"))

    val navigationOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            activity.onBackPressed()
        }

    val searchTextWatcher: TextWatcher
        get() = object : TextWatcherHelper() {
            override fun afterTextChanged(editable: Editable?) {
                //TODO:update recyclerview
                Log.d("TAG", editable.toString())
            }
        }

    init {
        binding.searchResultRv.layoutManager = LinearLayoutManager(activity)
        adapter.onItemClickListener = {
            position ->
            Log.d("TAG", "the position: " + position)
        }
    }
}