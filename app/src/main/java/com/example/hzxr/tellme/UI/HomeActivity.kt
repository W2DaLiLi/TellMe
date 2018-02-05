package com.example.hzxr.tellme.UI

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.UI.VM.HomeViewModel
import com.example.hzxr.tellme.databinding.ActivityHomeBinding

/**
 * Created by Hzxr on 2018/2/5.
 */
class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        val viewModel = HomeViewModel(this, binding)
        binding.vm = viewModel
    }
}