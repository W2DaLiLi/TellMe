package com.example.hzxr.tellme.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.db.model.Member

/**
 * Created by Hzxr on 2018/2/25.
 */
class MemberRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<MemberRecyclerAdapter.MemberViewHolder>() {

    var onItemOnClickListener: ((position: Int) -> Unit)? = null

    var members: List<Member> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MemberViewHolder{
        return MemberViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member, parent, false))
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(members[position].username)
        holder.itemView.setOnClickListener {
            onItemOnClickListener?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return members.size
    }

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameTv: TextView

        init {
            usernameTv = itemView.findViewById(R.id.item_member_username_tv)
        }

        fun bind(name: String) {
            usernameTv.text = name
        }
    }
}