package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.databinding.ActivityAddFriendBinding
import com.example.hzxr.tellme.ui.VM.AddFriendViewModel

/**
 * Created by Hzxr on 2018/2/12.
 */
class AddFriendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAddFriendBinding>(this, R.layout.activity_add_friend)
        val viewModel = AddFriendViewModel(this, binding)
        binding.vm = viewModel
    }
}