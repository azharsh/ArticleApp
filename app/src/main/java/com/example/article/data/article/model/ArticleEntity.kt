package com.example.article.data.article.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity (
    @PrimaryKey(autoGenerate = false) var id: Int,
    var title: String? = null
)