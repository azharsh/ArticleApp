package com.example.article.di.feature

import com.example.article.data.article.ArticleDataStore
import com.example.article.data.article.ArticleRepository
import com.example.article.ui.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleModule = module {

    single<ArticleRepository> { ArticleDataStore(get()) }

    viewModel { ArticleViewModel(get()) }

}