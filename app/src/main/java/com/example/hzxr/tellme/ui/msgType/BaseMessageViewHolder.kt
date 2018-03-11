package com.example.hzxr.tellme.ui.msgType

import android.app.Activity
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.example.hzxr.tellme.BR
import com.example.hzxr.tellme.databinding.ItemRvMessageImageIncomingBinding
import com.example.hzxr.tellme.databinding.ItemRvMessageImageOutgoingBinding
import com.example.hzxr.tellme.databinding.ItemRvMessageNormalIncomingBinding
import com.example.hzxr.tellme.databinding.ItemRvMessageNormalOutgoingBinding
import com.example.hzxr.tellme.ui.VM.NewMsgBaseViewModel
import com.example.hzxr.tellme.ui.VM.NewMsgImageViewModel
import com.example.hzxr.tellme.ui.VM.NewMsgNormalViewModel

/**
 * Created by Hzxr on 2018/3/3.
 */
class BaseMessageViewHolder<out B>(val binding: B, val activity: Activity) : RecyclerView.ViewHolder(binding.root) where B : ViewDataBinding{

    val viewModel: NewMsgBaseViewModel

    init {
        viewModel = createMsgViewModel()
        binding.setVariable(BR.vm, viewModel)
    }

    private fun createMsgViewModel(): NewMsgBaseViewModel {
        return when (binding) {
            is ItemRvMessageNormalIncomingBinding, is ItemRvMessageNormalOutgoingBinding ->
                    NewMsgNormalViewModel(activity, binding)
            is ItemRvMessageImageIncomingBinding, is ItemRvMessageImageOutgoingBinding ->
                    NewMsgImageViewModel(activity, binding)
            else -> NewMsgNormalViewModel(activity, binding)
        }
    }
}