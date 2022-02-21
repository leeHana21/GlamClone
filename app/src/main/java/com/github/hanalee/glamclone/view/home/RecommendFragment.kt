package com.github.hanalee.glamclone.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.github.hanalee.glamclone.databinding.FragmentRecommendBinding
import com.github.hanalee.glamclone.domain.viewmodel.MainViewModel
import com.github.hanalee.glamclone.extension.toGone
import com.github.hanalee.glamclone.extension.toInvisible
import com.github.hanalee.glamclone.extension.toVisible
import com.github.hanalee.glamclone.view.adapter.RecommendPagerAdapter
import com.github.hanalee.glamclone.view.home.recommend_item.AdditionalRecommendItemFragment
import com.github.hanalee.glamclone.view.home.recommend_item.CustomRecommendItemFragment
import com.github.hanalee.glamclone.view.home.recommend_item.TodayRecommendItemFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RecommendFragment : Fragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!
    private lateinit var recommendAdapter: RecommendPagerAdapter
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
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        getRecommendDataAtStart()
    }

    private fun initView() = with(binding) {
        recommendAdapter = RecommendPagerAdapter(this@RecommendFragment)
        vpRecommend.adapter = recommendAdapter

        vpRecommend.toInvisible()
        pbLoading.toVisible()

        vpRecommend.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == recommendAdapter.itemCount - 1) {
                    getMoreRecommendInfo()
                }
            }
        })
    }

    private fun initObserver() = with(mainViewModel) {
        firstRecommendData.observe(viewLifecycleOwner) {
            for (i in it.first.indices) {
                recommendAdapter.addFragment(TodayRecommendItemFragment(it.first[i]))
            }

            recommendAdapter.addFragment(CustomRecommendItemFragment())

            for (i in it.second.indices) {
                recommendAdapter.addFragment(AdditionalRecommendItemFragment(it.second[i]))
            }
            binding.apply {
                vpRecommend.toVisible()
                pbLoading.toGone()
            }
        }
        moreRecommendData.observe(viewLifecycleOwner) {
            for (i in it.indices) {
                recommendAdapter.addFragment(AdditionalRecommendItemFragment(it[i]))
            }
        }
        customRecommendData.observe(viewLifecycleOwner) {
            for (i in it.indices) {
                recommendAdapter.addFragmentTop(AdditionalRecommendItemFragment(it[i]))
            }
            binding.vpRecommend.apply {
                this.post {
                    setCurrentItem(0, true)
                }
            }
        }

        deleteItem.observe(viewLifecycleOwner) {
            recommendAdapter.removeFragment(it)
            binding.vpRecommend.apply {
                this.post {
                    setCurrentItem(it, true)
                }
            }
        }

    }

    private fun getMoreRecommendInfo() = mainViewModel.getMoreRecommendData()

    private fun getRecommendDataAtStart() = mainViewModel.getRecommendDataAtStart()
}