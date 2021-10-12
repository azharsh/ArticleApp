package com.example.article.di

import com.example.article.utils.PrefManager
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PREFERENCE_NAME = "preference_name"

val preferenceModule = module {

    single(named(PREFERENCE_NAME)) { "pref_name" }

    single { PrefManager.getInstance() }

}