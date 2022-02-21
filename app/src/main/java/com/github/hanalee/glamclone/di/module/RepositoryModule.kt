package com.github.hanalee.glamclone.di.module

import com.github.hanalee.glamclone.domain.repository.MainRepository
import com.github.hanalee.glamclone.domain.repository.ProfileRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepository() }
    single { ProfileRepository() }
}