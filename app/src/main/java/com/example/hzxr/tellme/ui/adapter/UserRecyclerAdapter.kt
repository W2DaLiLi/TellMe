package com.example.hzxr.tellme.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hzxr.tellme.R

/**
 * Created by Hzxr on 2018/2/12.
 */
class UserRecyclerAdapter(private val context: Context, private val userList: ArrayList<String>): RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameTv: TextView

        init {
            usernameTv = itemView.findViewById(R.id.item_username_tv)
        }

        fun bind(name: String) {
            usernameTv.text = name
        }
    }
}