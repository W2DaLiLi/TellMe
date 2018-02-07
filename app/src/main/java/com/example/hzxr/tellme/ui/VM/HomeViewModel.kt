package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast

import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.databinding.ActivityHomeBinding

/**
 * Created by Hzxr on 2018/2/5.
 */
class HomeViewModel(activity: Activity, binding: ActivityHomeBinding) : BaseViewModel<ActivityHomeBinding>(activity, binding) {

    val onMenuItemClickListener: Toolbar.OnMenuItemClickListener
        get() = Toolbar.OnMenuItemClickListener {
            item ->
//            if (item.itemId == R.id.action_settings) {
//                Log.d("TAG","test setting button")
//            }
            Log.d("TAG","Test menu click")
            true
        }
}