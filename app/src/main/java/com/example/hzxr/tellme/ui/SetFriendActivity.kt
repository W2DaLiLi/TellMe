package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.databinding.ActivitySetFriendBinding
import com.example.hzxr.tellme.ui.VM.SetFriendViewModel

/**
 * Created by Hzxr on 2018/3/13.
 */
class SetFriendActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySetFriendBinding>(this, R.layout.activity_set_friend)
        val viewModel = SetFriendViewModel(this, binding)
        binding.vm = viewModel
    }
}