package com.example.article.data.article

import com.example.article.data.article.model.*
import com.example.article.data.article.remote.ArticleApiClient

class ArticleDataStore(private val articleApiClient: ArticleApiClient) : ArticleRepository {

    override suspend fun getPost(): List<PostModel> {
       return articleApiClient.getPost()
    }

    override suspend fun getComment(id: Int): List<CommentModel> {
       return articleApiClient.getComment(id)
    }

    override suspend fun getUser(id: Int): UserModel {
        return articleApiClient.getUser(id)
    }

    override suspend fun getAlbum(id: Int): AlbumModel {
        return articleApiClient.getAlbum(id)
    }

    override suspend fun getPhoto(id: Int): List<PhotoModel> {
        return articleApiClient.getPhoto(id)
    }

}