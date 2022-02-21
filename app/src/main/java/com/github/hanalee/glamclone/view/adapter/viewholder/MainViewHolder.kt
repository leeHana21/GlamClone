package com.github.hanalee.glamclone.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.github.hanalee.glamclone.databinding.InfoItemLayoutBinding
import com.github.hanalee.glamclone.view.adapter.EditInfoAdapter


class MainViewHolder(private val binding: InfoItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun binds(item: String, itemClickListener: EditInfoAdapter.OnItemClickListener?, type: String) {
        binding.apply {
            tvItem.text = item
            tvItem.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(item, type)
                }
            }
        }
    }
}