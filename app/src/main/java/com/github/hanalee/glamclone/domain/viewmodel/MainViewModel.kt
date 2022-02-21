package com.github.hanalee.glamclone.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hanalee.glamclone.domain.repository.MainRepository
import com.github.hanalee.glamclone.domain.retrofit.entity.response.AdditionalRecommendResponse
import com.github.hanalee.glamclone.domain.retrofit.entity.response.TodayRecommendResponse
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * 메인 ViewModel
 */

class MainViewModel : ViewModel(), KoinComponent {
    private val mainRepository: MainRepository by inject()
    private lateinit var metaData: AdditionalRecommendResponse.Meta

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "mainViewModel: $exception")
    }

    private val _firstRecommendData =
        MutableLiveData<Pair<List<TodayRecommendResponse.Data>, List<AdditionalRecommendResponse.Data>>>()
    val firstRecommendData: LiveData<Pair<List<TodayRecommendResponse.Data>, List<AdditionalRecommendResponse.Data>>>
        get() = _firstRecommendData

    private val _moreRecommendData =
        MutableLiveData<List<AdditionalRecommendResponse.Data>>()
    val moreRecommendData: LiveData<List<AdditionalRecommendResponse.Data>>
        get() = _moreRecommendData

    private val _customRecommendData =
        MutableLiveData<List<AdditionalRecommendResponse.Data>>()
    val customRecommendData: LiveData<List<AdditionalRecommendResponse.Data>>
        get() = _customRecommendData

    private val _deleteItem =
        MutableLiveData<Int>()
    val deleteItem: LiveData<Int>
        get() = _deleteItem

    fun getRecommendDataAtStart() = getRecommendDataFromRemote()

    private fun getRecommendDataFromRemote() = viewModelScope.launch(handler) {
        val todayRecommendResponse = mainRepository.getTodayRecommend()
        val additionalRecommendResponse = mainRepository.getAdditionalRecommend()
        metaData = additionalRecommendResponse.meta

        _firstRecommendData.value =
            Pair(todayRecommendResponse.data, additionalRecommendResponse.data)

    }

    fun getMoreRecommendData() = getMoreRecommendDataFromRemote()

    private fun getMoreRecommendDataFromRemote() = viewModelScope.launch(handler) {
        metaData.let {
            val response = mainRepository.getMoreRecommend(metaData.next.id)
            metaData = response.meta
            _moreRecommendData.value = response.data
        }
    }

    fun getCustomRecommendData() = getCustomRecommendDataFromRemote()

    private fun getCustomRecommendDataFromRemote() = viewModelScope.launch(handler) {
        if (_customRecommendData.value == null) {
            val response = mainRepository.getCustomRecommend()
            _customRecommendData.value = response.data
        }
    }

    fun deleteRecommendItem(position: Int) {
        Log.d(TAG, "deleteRecommendItem: $position")
        _deleteItem.value = position
    }
}