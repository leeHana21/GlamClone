package com.github.hanalee.glamclone.view.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.POSITION
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.TAG

class RecommendPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var fragments: ArrayList<Fragment> = ArrayList()
    private var pageIds = fragments.map { it.hashCode().toLong() }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        fragments[position].arguments = Bundle().also {
            it.putInt(POSITION, position)
        }
        return fragments[position]
    }

    override fun getItemId(position: Int): Long {
        return fragments[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return pageIds.contains(itemId)
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        pageIds = fragments.map { it.hashCode().toLong() }
        Log.d(TAG, "addFragment: $fragments")
        Log.d(TAG, "addFragment: $pageIds")
        notifyItemInserted(fragments.size - 1)
    }

    fun addFragmentTop(fragment: Fragment) {
        fragments.add(0, fragment)
        Log.d(TAG, "addFragmentTop: $fragments")
        notifyItemInserted(0)
        notifyItemRangeChanged(0, fragments.size)
    }

    fun removeFragment(position: Int) {
        fragments.removeAt(position)
        pageIds = fragments.map { it.hashCode().toLong() }
        Log.d(TAG, "deleteFragment: $fragments")
        Log.d(TAG, "deleteFragment: $pageIds")
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, fragments.size)
    }
}