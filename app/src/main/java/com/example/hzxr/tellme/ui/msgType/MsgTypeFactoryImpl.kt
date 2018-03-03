package com.example.hzxr.tellme.ui.msgType

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.databinding.ItemRvMessageImageIncomingBinding
import com.example.hzxr.tellme.databinding.ItemRvMessageImageOutgoingBinding
import com.example.hzxr.tellme.databinding.ItemRvMessageNormalIncomingBinding
import com.example.hzxr.tellme.databinding.ItemRvMessageNormalOutgoingBinding
import com.example.hzxr.tellme.db.model.Msg

/**
 * Created by Hzxr on 2018/3/3.
 */
class MsgTypeFactoryImpl(val activity: Activity) : MsgTypeFactory {

    companion object {
        private const val SELF_VIEW_TYPE_NORMAL = R.layout.item_rv_message_normal_outgoing
        private const val SELF_VIEW_TYPE_IMAGE = R.layout.item_rv_message_image_outgoing

        private const val OTHER_VIEW_TYPE_NORMAL = R.layout.item_rv_message_normal_incoming
        private const val OTHER_VIEW_TYPE_IMAGE = R.layout.item_rv_message_image_incoming
    }

    override fun type(msg: Msg): Int {
        return when (msg.type) {
            Constants.MESSAGE_TYPE_SELF_TEXT -> SELF_VIEW_TYPE_NORMAL
            Constants.MESSAGE_TYPE_SELF_IMAGE -> SELF_VIEW_TYPE_IMAGE
            Constants.MESSAGE_TYPE_OTHER_TEXT -> OTHER_VIEW_TYPE_NORMAL
            Constants.MESSAGE_TYPE_OTHER_IMAGE -> OTHER_VIEW_TYPE_IMAGE
            else -> throw Exception("unknown message type")
        }
    }

    override fun createViewHolder(type: Int, parent: ViewGroup?): BaseMessageViewHolder<*>? {
        return when (type) {
            SELF_VIEW_TYPE_NORMAL -> createMsgViewHolder<ItemRvMessageNormalOutgoingBinding>(type, parent)
            SELF_VIEW_TYPE_IMAGE -> createMsgViewHolder<ItemRvMessageImageOutgoingBinding>(type, parent)
            OTHER_VIEW_TYPE_NORMAL -> createMsgViewHolder<ItemRvMessageNormalIncomingBinding>(type, parent)
            OTHER_VIEW_TYPE_IMAGE -> createMsgViewHolder<ItemRvMessageImageIncomingBinding>(type, parent)
            else -> {
                Log.d("TAG", "no correct message DataBinding")
                null
            }
        }
    }

    private fun <B> createMsgViewHolder(layoutId: Int, parent: ViewGroup?): BaseMessageViewHolder<*> where B : ViewDataBinding {
        return BaseMessageViewHolder<B>(
                DataBindingUtil.inflate(LayoutInflater.from(parent?.context), layoutId, parent, false), activity
        )
    }
}