package com.example.hzxr.tellme.UI

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.UI.VM.MainViewModel
import com.example.hzxr.tellme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel = MainViewModel(this, binding)
        binding.vm = viewModel
    }
}
