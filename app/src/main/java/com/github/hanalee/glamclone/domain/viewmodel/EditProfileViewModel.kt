package com.github.hanalee.glamclone.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hanalee.glamclone.domain.repository.ProfileRepository
import com.github.hanalee.glamclone.domain.retrofit.entity.response.ProfileResponse
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.BODY_TYPE
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.EDUCATION
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditProfileViewModel : ViewModel(), KoinComponent {
    private val profileRepository: ProfileRepository by inject()

    private val _profileInfo = MutableLiveData<ProfileResponse.Data>()
    val profileInfo: LiveData<ProfileResponse.Data>
        get() = _profileInfo

    private val _metaData = MutableLiveData<ProfileResponse.Meta>()
    val metaData: LiveData<ProfileResponse.Meta>
        get() = _metaData

    private val _genderAndBodyType = MutableLiveData<Pair<String, String>>()
    val genderAndBodyType: LiveData<Pair<String, String>>
        get() = _genderAndBodyType

    private val _education = MutableLiveData<String>()
    val education: LiveData<String>
        get() = _education

    private val _bodyType = MutableLiveData<String>()
    val bodyType: LiveData<String>
        get() = _bodyType

    private lateinit var profileMetaData: ProfileResponse.Meta

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "$exception")
    }

    fun changeInfo(changeData: String, type: String) {
        when (type) {
            BODY_TYPE -> {
                _bodyType.value = changeData
            }
            EDUCATION -> {
                _education.value = changeData
            }
            else -> {}
        }
    }

    fun getProfileInfo() = getProfileInfoFromRemote()


    private fun getProfileInfoFromRemote() = viewModelScope.launch(handler) {
        val response = profileRepository.getProfileInfo()

        _profileInfo.value = response.data
        profileMetaData = response.meta

        val gender =
            profileMetaData.genders.find { it.key == response.data.gender }?.name ?: ""
        val bodyType =
            profileMetaData.bodyTypes.find { it.key == response.data.bodyType }?.name ?: ""

        _genderAndBodyType.value = Pair(gender, bodyType)
        _metaData.value = profileMetaData
    }
}