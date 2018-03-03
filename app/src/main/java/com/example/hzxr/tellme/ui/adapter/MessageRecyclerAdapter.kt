package com.example.hzxr.tellme.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.hzxr.tellme.ui.msgType.MsgTypeFactory
import com.example.hzxr.tellme.ui.msgType.MsgTypeFactoryImpl

/**
 * Created by Hzxr on 2018/3/2.
 */
class MessageRecyclerAdapter : RecyclerView.Adapter<MessageRecyclerAdapter.MessageViewHolder>() {

    var msgTypeFactory: MsgTypeFactory

    init {
        msgTypeFactory = MsgTypeFactoryImpl()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MessageViewHolder{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}