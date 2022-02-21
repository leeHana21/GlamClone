package com.github.hanalee.glamclone.di.module

import com.github.hanalee.glamclone.domain.viewmodel.EditProfileViewModel
import com.github.hanalee.glamclone.domain.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { EditProfileViewModel() }
}