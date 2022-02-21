package com.github.hanalee.glamclone.view.home.recommend_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.hanalee.glamclone.BuildConfig
import com.github.hanalee.glamclone.R
import com.github.hanalee.glamclone.databinding.FragmentRecomendItemBinding
import com.github.hanalee.glamclone.domain.retrofit.entity.response.TodayRecommendResponse
import com.github.hanalee.glamclone.domain.viewmodel.MainViewModel
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.POSITION
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TodayRecommendItemFragment(data: TodayRecommendResponse.Data) : Fragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private var _binding: FragmentRecomendItemBinding? = null
    private val binding get() = _binding!!
    private val todayRecommendData = data
    private val position: Int by lazy { requireArguments().getInt(POSITION) }

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
        _binding = FragmentRecomendItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        Glide.with(ivProfile.context)
            .load("${BuildConfig.BASE_URL}${todayRecommendData.pictures[0]}")
            .placeholder(R.drawable.person)
            .thumbnail(0.1f)
            .error(R.drawable.ic_launcher_foreground)
            .into(ivProfile)

        "${todayRecommendData.name},${todayRecommendData.age}".also {
            tvProfile.text = it
        }
        tvIntroduce.text = todayRecommendData.introduction
            ?: "${todayRecommendData.job} · ${todayRecommendData.location} · ${todayRecommendData.height}"
        btDelete.setOnClickListener { deleteItem() }
        btLike.setOnClickListener { deleteItem() }
    }

    private fun deleteItem() = mainViewModel.deleteRecommendItem(position)

}