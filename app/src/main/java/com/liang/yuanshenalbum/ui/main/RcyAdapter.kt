package com.liang.yuanshenalbum.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.model.Role

class RcyAdapter(private val list: List<Role>) : RecyclerView.Adapter<RcyAdapter.ViewHolder>() {

    private lateinit var listener: OnRcyItemClickListener

    fun setOnRcyItemClickListener(listener: OnRcyItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tv_rcy_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcyAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rcy_main, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener.onClick(list[holder.adapterPosition])
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: RcyAdapter.ViewHolder, position: Int) {
        holder.text.text = list[position].name
    }

    override fun getItemCount() = list.size
}