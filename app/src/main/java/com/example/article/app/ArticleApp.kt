package com.example.article.app

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.article.di.apiModule
import com.example.article.di.dbModule
import com.example.article.di.feature.articleModule
import com.example.article.di.preferenceModule
import com.example.article.di.utilityModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArticleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        startKoin {

            androidLogger()

            androidContext(this@ArticleApp)

            modules(
                listOf(
                    apiModule,
                    dbModule,
                    preferenceModule,
                    utilityModule,
                    articleModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }
}