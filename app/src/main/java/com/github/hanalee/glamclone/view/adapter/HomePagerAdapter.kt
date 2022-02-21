package com.github.hanalee.glamclone.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.GLAM_MAIN
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.NEARBY
import com.github.hanalee.glamclone.view.home.FeedFragment
import com.github.hanalee.glamclone.view.home.NearbyFragment
import com.github.hanalee.glamclone.view.home.RecommendFragment

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val pageItemCount = 3
    override fun getItemCount(): Int = pageItemCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            GLAM_MAIN -> {
                RecommendFragment()
            }
            NEARBY -> {
                NearbyFragment()
            }
            else -> {
                FeedFragment()
            }
        }
    }
}