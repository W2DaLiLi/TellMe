package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.databinding.ActivityChatBinding
import com.example.hzxr.tellme.ui.VM.ChatViewModel

/**
 * Created by Hzxr on 2018/2/27.
 */
class ChatActivity : AppCompatActivity() {

    private lateinit var viewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityChatBinding>(this, R.layout.activity_chat)
        val targetName = intent.getStringExtra("targetId")?: return
        viewModel = ChatViewModel(this, binding, targetName)
        binding.vm = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}