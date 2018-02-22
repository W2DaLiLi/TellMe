package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.ui.VM.HomeViewModel
import com.example.hzxr.tellme.ui.adapter.ViewPagerAdapter
import com.example.hzxr.tellme.databinding.ActivityHomeBinding

/**
 * Created by Hzxr on 2018/2/5.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        val username = intent.getStringExtra("username")?: return
        val viewModel = HomeViewModel(this, binding, username)
        binding.vm = viewModel
        initView(binding)
    }

    private fun initView(binding: ActivityHomeBinding) {
        val list = listOf<Fragment>(SessionFragment(), FriendsFragment())
        val titles = listOf("Session", "Friends")
        val adapter = ViewPagerAdapter(list, titles, supportFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}