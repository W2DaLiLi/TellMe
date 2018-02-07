package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.ui.VM.LoginViewModel
import com.example.hzxr.tellme.databinding.ActivityLoginBinding


/**
 * Created by Hzxr on 2018/1/20.
 */
class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        val viewModel = LoginViewModel(this, binding)
        binding.vm = viewModel
    }

}
