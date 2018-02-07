package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.ui.VM.FriendsViewModel
import com.example.hzxr.tellme.databinding.FragmentFriendsBinding

/**
 * Created by Hzxr on 2018/2/5.
 */
class FriendsFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentFriendsBinding>(inflater, R.layout.fragment_friends, container, false)
        val viewModel = FriendsViewModel(activity, binding)
        binding.vm = viewModel
        return binding.root
    }
}