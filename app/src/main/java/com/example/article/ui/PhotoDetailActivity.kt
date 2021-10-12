package com.example.article.ui

import android.os.Bundle
import com.example.article.base.BaseActivity
import com.example.article.databinding.ActivityPhotoBinding
import com.example.article.ui.viewmodel.ArticleViewModel
import com.example.article.utils.launch
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import uk.co.senab.photoview.PhotoViewAttacher

class PhotoDetailActivity : BaseActivity() {

    private lateinit var binding : ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getData()
    }

    private fun getData(){

        binding.titlePhoto.text = intent.getStringExtra("title")
        Picasso.get().load(intent.getStringExtra("url")).into(binding.imgPhoto)

        val photoPatcher = PhotoViewAttacher(binding.imgPhoto)
        photoPatcher.update()

    }

    override fun attachView() {

    }

    override fun detachView() {

    }
}