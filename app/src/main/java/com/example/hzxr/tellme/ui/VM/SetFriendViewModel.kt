package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.databinding.ActivitySetFriendBinding

/**
 * Created by Hzxr on 2018/3/13.
 */
class SetFriendViewModel(activity: Activity, binding: ActivitySetFriendBinding) : BaseViewModel<ActivitySetFriendBinding>(activity, binding) {

    private val TAG = "SetFriendViewModel"

    init {
        binding.toolbar.setNavigationOnClickListener { }
    }

    val navigationOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            Log.d(TAG, "navigationOnClickListener")
        }

}