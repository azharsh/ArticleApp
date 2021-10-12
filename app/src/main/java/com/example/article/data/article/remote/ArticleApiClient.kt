package com.example.article.data.article.remote

import com.example.article.data.article.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleApiClient {

    @GET("posts")
    suspend fun getPost() : List<PostModel>

    @GET("posts/{id}/comments")
    suspend fun getComment(
        @Path("id") id: Int
    ) : List<CommentModel>

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ) : UserModel

    @GET("albums/{id}")
    suspend fun getAlbum(
        @Path("id") id: Int
    ) : AlbumModel

    @GET("albums/{id}/photos")
    suspend fun getPhoto(
        @Path("id") id: Int
    ) : List<PhotoModel>

}