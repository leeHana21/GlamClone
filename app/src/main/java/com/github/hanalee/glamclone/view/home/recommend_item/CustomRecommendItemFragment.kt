package com.github.hanalee.glamclone.view.home.recommend_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.hanalee.glamclone.databinding.CustomRecommendLayoutBinding
import com.github.hanalee.glamclone.domain.viewmodel.MainViewModel
import com.github.hanalee.glamclone.extension.toToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CustomRecommendItemFragment : Fragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private var _binding: CustomRecommendLayoutBinding? = null
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
        _binding = CustomRecommendLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        btGlamRecommend.setOnClickListener { getCustomRecommendData() }
        btGlamour.setOnClickListener { getCustomRecommendData() }
        btTopCharming.setOnClickListener { getCustomRecommendData() }
        btPet.setOnClickListener { getCustomRecommendData() }
        btMore.setOnClickListener {
            activity?.toToast("입사를 못해서 구현을 못했어요..!")
        }
    }

    private fun getCustomRecommendData() = mainViewModel.getCustomRecommendData()
}