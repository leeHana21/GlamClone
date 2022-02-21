package com.github.hanalee.glamclone.di.module

import com.github.hanalee.glamclone.view.adapter.EditInfoAdapter

import org.koin.dsl.module

val adapterModule = module {
    single { EditInfoAdapter() } // 요청할 때마다 생성
}