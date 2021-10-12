package com.example.article.data.article

import com.example.article.data.article.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleRepository {

    suspend fun getPost(): List<PostModel>

    suspend fun getComment(
        id: Int
    ): List<CommentModel>

    suspend fun getUser(
        id: Int
    ): UserModel

    suspend fun getAlbum(
        id: Int
    ): AlbumModel

    suspend fun getPhoto(
        id: Int
    ): List<PhotoModel>
}