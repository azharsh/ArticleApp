package com.example.article.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.article.data.article.model.ArticleEntity
import com.example.simplemovie.data.ListStringConverter


@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    ListStringConverter::class,
//    ListPopularStringConverter::class,
//    ListTopRatedStringConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(
            context: Context,
            databaseName: String
        ): AppDatabase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    databaseName
                )
                    .fallbackToDestructiveMigration()
                INSTANCE = builder.build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
//
//   abstract fun popularDao(): PopularDao
//   abstract fun topRatedDao(): TopRatedDao

}
