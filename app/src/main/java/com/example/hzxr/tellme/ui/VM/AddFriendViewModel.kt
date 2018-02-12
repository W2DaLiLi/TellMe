package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.view.View
import com.example.hzxr.tellme.databinding.ActivityAddFriendBinding

/**
 * Created by Hzxr on 2018/2/12.
 */
class AddFriendViewModel(activity: Activity, binding: ActivityAddFriendBinding) : BaseViewModel<ActivityAddFriendBinding>(activity, binding) {

    val navigationOnClickListener : View.OnClickListener
        get() = View.OnClickListener {
            activity.onBackPressed()
        }
}