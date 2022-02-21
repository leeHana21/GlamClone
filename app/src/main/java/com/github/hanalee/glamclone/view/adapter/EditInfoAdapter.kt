package com.github.hanalee.glamclone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.hanalee.glamclone.databinding.InfoItemLayoutBinding
import com.github.hanalee.glamclone.domain.retrofit.entity.response.ProfileResponse
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.BODY_TYPE
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.EDUCATION
import com.github.hanalee.glamclone.view.adapter.viewholder.MainViewHolder

class EditInfoAdapter : RecyclerView.Adapter<MainViewHolder>() {


    private lateinit var itemList: ProfileResponse.Meta
    private lateinit var type: String

    fun setData(userAction: String, data: ProfileResponse.Meta) {
        type = userAction
        itemList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            InfoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        when (type) {
            BODY_TYPE -> {
                holder.binds(itemList.bodyTypes[position].name, itemClickListener, BODY_TYPE)
            }

            EDUCATION -> {
                holder.binds(itemList.educations[position].name, itemClickListener, EDUCATION)
            }
        }
    }

    override fun getItemCount(): Int {
        return when (type) {
            BODY_TYPE -> {
                itemList.bodyTypes.size
            }

            EDUCATION -> {
                itemList.educations.size
            }
            else -> {
                0
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(type: String, item: String)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        itemClickListener = listener
    }
}