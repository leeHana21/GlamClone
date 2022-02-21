package com.github.hanalee.glamclone.domain.repository

import com.github.hanalee.glamclone.domain.retrofit.MainApiClient

class MainRepository {
    suspend fun getTodayRecommend() =
        MainApiClient.getInstance().getTodayRecommend()

    suspend fun getAdditionalRecommend() =
        MainApiClient.getInstance().getAdditionalRecommend()

    suspend fun getCustomRecommend() =
        MainApiClient.getInstance().getCustomRecommend()

    suspend fun getMoreRecommend(id: Int) =
        MainApiClient.getInstance().getMoreRecommend(id)
}