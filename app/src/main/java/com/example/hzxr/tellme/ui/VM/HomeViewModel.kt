package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v7.widget.Toolbar
import android.util.Log

import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.databinding.ActivityHomeBinding

/**
 * Created by Hzxr on 2018/2/5.
 */
class HomeViewModel(activity: Activity, binding: ActivityHomeBinding) : BaseViewModel<ActivityHomeBinding>(activity, binding) {

    val onMenuItemClickListener: Toolbar.OnMenuItemClickListener
        get() = Toolbar.OnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_settings) {
                Log.d("TAG", "test setting button")
            }
            true
        }
// 在使用databinding binding 这个监听的时候总是报错，类型问题，目前没有找到好的解决办法，先暂时这样写。
//    val onNavigationItemSelectedListener: NavigationView.OnNavigationItemSelectedListener
//        get() = NavigationView.OnNavigationItemSelectedListener {
//            item ->
//            when(item.itemId) {
//                R.id.navigation_profile -> {
//                    Log.d("TAG", "navigation profile")
//                }
//                R.id.navigation_settings -> {
//                    Log.d("TAG", "navigation settings")
//                }
//            }
//            true
//        }
    init {
        binding.navigationView.setNavigationItemSelectedListener {
            item ->
            when(item.itemId) {
                R.id.navigation_profile -> {
                    Log.d("TAG", "navigation profile")
                }
                R.id.navigation_settings -> {
                    Log.d("TAG", "navigation settings")
                }
            }
            true
        }
    }
}