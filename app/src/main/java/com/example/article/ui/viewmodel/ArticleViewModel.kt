package com.example.article.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.article.data.article.ArticleRepository
import com.example.article.data.article.model.*

class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    suspend fun getPost(): MutableLiveData<List<PostModel>> {
        val result = MutableLiveData<List<PostModel>>()
        try {
            val response = articleRepository.getPost()
            result.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    suspend fun geCommment(id : Int): MutableLiveData<List<CommentModel>> {
        val result = MutableLiveData<List<CommentModel>>()
        try {
            val response = articleRepository.getComment(id)
            result.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    suspend fun getUsers(id : Int): MutableLiveData<UserModel> {
        val result = MutableLiveData<UserModel>()
        try {
            val response = articleRepository.getUser(id)
            result.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    suspend fun getAlbum(id : Int): MutableLiveData<AlbumModel> {
        val result = MutableLiveData<AlbumModel>()
        try {
            val response = articleRepository.getAlbum(id)
            result.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    suspend fun getPhoto(id : Int): MutableLiveData<List<PhotoModel>> {
        val result = MutableLiveData<List<PhotoModel>>()
        try {
            val response = articleRepository.getPhoto(id)
            result.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}