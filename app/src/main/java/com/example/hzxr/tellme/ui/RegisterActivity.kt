package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.ui.VM.RegisterViewModel
import com.example.hzxr.tellme.databinding.ActivityRegisterBinding

/**
 * Created by Hzxr on 2018/2/1.
 */
class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)
        val viewModel = RegisterViewModel(this, binding)
        binding.vm = viewModel
    }

}