package com.example.hzxr.tellme.ui.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.example.hzxr.tellme.db.model.Msg
import com.example.hzxr.tellme.ui.msgType.BaseMessageViewHolder
import com.example.hzxr.tellme.ui.msgType.MsgTypeFactory
import com.example.hzxr.tellme.ui.msgType.MsgTypeFactoryImpl

/**
 * Created by Hzxr on 2018/3/2.
 */
class MessageRecyclerAdapter(private val activity: Activity,
                             var msgList: List<Msg>) : RecyclerView.Adapter<BaseMessageViewHolder<*>?>() {

    var msgTypeFactory: MsgTypeFactory

    init {
        msgTypeFactory = MsgTypeFactoryImpl(activity)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseMessageViewHolder<*>{
        return msgTypeFactory.createViewHolder(viewType, parent) ?: throw Exception("can't create ViewHolder for $viewType")
    }

    override fun onBindViewHolder(holder: BaseMessageViewHolder<*>?, position: Int) {
        val msg = msgList[position]
        if (holder == null || msg == null) return
        holder.viewModel.setMsg(msg)
        holder.viewModel.notifyChange()
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    override fun getItemViewType(position: Int): Int {
        return msgList[position].type(msgTypeFactory)
    }
}