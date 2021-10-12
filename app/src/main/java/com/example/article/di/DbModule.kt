package com.example.article.di




import com.example.article.data.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module

const val DATABASE_NAME = "movie_database"

val dbModule = module {

    single { provideDb() }

//    single { providePopularDao(get()) }
//
//    single { provideTopRatedDao(get()) }

}

private fun Scope.provideDb() =
    AppDatabase.getAppDatabase(androidApplication(), DATABASE_NAME)

//fun providePopularDao(db: AppDatabase): PopularDao {
//    return db.popularDao()
//}
//
//fun provideTopRatedDao(db: AppDatabase): TopRatedDao {
//    return db.topRatedDao()
//}