package com.github.hanalee.glamclone.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.hanalee.glamclone.R
import com.github.hanalee.glamclone.databinding.FragmentHomeBinding
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.FEED
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.GLAM_MAIN
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.NEARBY
import com.github.hanalee.glamclone.view.EditProfileActivity
import com.github.hanalee.glamclone.view.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        ivEditInfo.setOnClickListener {
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }
        vpHome.adapter = HomePagerAdapter(this@HomeFragment)
        TabLayoutMediator(tabHome, vpHome) { tab, position ->
            when (position) {
                GLAM_MAIN -> {
                    tab.setCustomView(R.layout.tab_item_layout_glam_main)
                }
                NEARBY -> {
                    tab.text = "근처"
                }
                FEED -> {
                    tab.text = "피드"
                }
            }
        }.attach()

    }
}