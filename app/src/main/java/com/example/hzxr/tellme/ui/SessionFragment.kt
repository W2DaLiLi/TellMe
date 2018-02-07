package com.example.hzxr.tellme.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.ui.VM.SessionViewModel
import com.example.hzxr.tellme.databinding.FragmentSessionBinding

/**
 * Created by Hzxr on 2018/2/5.
 */
class SessionFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSessionBinding>(inflater, R.layout.fragment_session, container, false)
        val viewModel = SessionViewModel(activity, binding)
        binding.vm = viewModel
        return binding.root
    }

}