package com.github.hanalee.glamclone.domain.repository

import com.github.hanalee.glamclone.domain.retrofit.MainApiClient

class ProfileRepository {
    suspend fun getProfileInfo() =
        MainApiClient.getInstance().getProfileInfo()
}