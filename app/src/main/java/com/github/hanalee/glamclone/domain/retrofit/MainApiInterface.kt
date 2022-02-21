package com.github.hanalee.glamclone.domain.retrofit

import com.github.hanalee.glamclone.domain.retrofit.entity.response.AdditionalRecommendResponse
import com.github.hanalee.glamclone.domain.retrofit.entity.response.ProfileResponse
import com.github.hanalee.glamclone.domain.retrofit.entity.response.TodayRecommendResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface MainApiInterface {

    @Headers("Content-Type: application/json")
    @GET("/introduction")
    suspend fun getTodayRecommend(): TodayRecommendResponse

    @Headers("Content-Type: application/json")
    @GET("/introduction/additional")
    suspend fun getAdditionalRecommend(): AdditionalRecommendResponse

    @Headers("Content-Type: application/json")
    @POST("/introduction/custom")
    suspend fun getCustomRecommend(): AdditionalRecommendResponse

    @Headers("Content-Type: application/json")
    @GET("/profile")
    suspend fun getProfileInfo(): ProfileResponse

    @Headers("Content-Type: application/json")
    @GET("/introduction/additional/{id}")
    suspend fun getMoreRecommend(@Path("id") id: Int): AdditionalRecommendResponse
}