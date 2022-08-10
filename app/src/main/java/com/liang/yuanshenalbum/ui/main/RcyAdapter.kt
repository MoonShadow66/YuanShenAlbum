package com.liang.yuanshenalbum.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.model.Role
import com.liang.yuanshenalbum.util.ImageResource

class RcyAdapter(val context: Context, private val list: List<Role>) :
    RecyclerView.Adapter<RcyAdapter.ViewHolder>() {

    private lateinit var listener: OnRcyItemClickListener

    fun setOnRcyItemClickListener(listener: OnRcyItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tv_rcy_name)
        val img: ImageView = view.findViewById(R.id.iv_rcy_img)
        val cardView: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcyAdapter.ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_rcy_main, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener.onClick(list[holder.adapterPosition], holder.adapterPosition)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RcyAdapter.ViewHolder, position: Int) {
        val type = list[position].type
        val url = "${ImageResource.BASE_URL}/${type}/${type}1.jpg"
        Glide.with(context).load(url).into(holder.img)
        // 当前分类的图片数量
        //   val imgLength = ImageResource.roleMap[type]
        holder.text.text = "${list[position].name}"
        if (list[position].isChecked) {
            holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.purple_200))
            holder.text.setTextColor(context.resources.getColor(R.color.white))
        } else {
            holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.white))
            holder.text.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount() = list.size
}