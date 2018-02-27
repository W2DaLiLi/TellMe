package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.databinding.ActivityProfileBinding
import com.example.hzxr.tellme.db.DBUtil.AccountDataHelper

/**
 * Created by Hzxr on 2018/2/27.
 */
class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityProfileBinding>(this, R.layout.activity_profile)
        binding.account = AccountDataHelper.currentAccount
    }
}