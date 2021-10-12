package com.example.article.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.article.base.BaseActivity
import com.example.article.databinding.ActivityListBinding
import com.example.article.databinding.ActivityPhotoBinding
import com.example.article.databinding.ActivityUserBinding
import com.example.article.ui.adapter.PhotoAdapter
import com.example.article.ui.adapter.PostAdapter
import com.example.article.ui.viewmodel.ArticleViewModel
import com.example.article.utils.launch
import org.koin.android.ext.android.inject

class UserDetailActivity : BaseActivity() {

    private val articleViewModel : ArticleViewModel by inject()
    private lateinit var binding : ActivityUserBinding
    private lateinit var photoAdapter: PhotoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecycleView()
        getData()

    }

    private fun initRecycleView(){

        photoAdapter =  PhotoAdapter(arrayListOf(), this)
        binding.recPhoto.layoutManager = GridLayoutManager(this, 3)
        binding.recPhoto.adapter = photoAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun getData(){
       launch {
           articleViewModel.getUsers(intent.getIntExtra("userId", 0)).observe(this, Observer {

               binding.userName.text = it.name
               binding.userAddress.text = " ${it.address.street} ${it.address.suite} ${it.address.city} ${it.address.zipcode}"
               binding.userComp.text = it.company.name
               binding.userEmail.text = it.email

           })

           var albumId = 0

           articleViewModel.getAlbum(intent.getIntExtra("userId", 0)).observe(this, Observer {
               binding.ablbumName.text = it.title
               albumId = it.id
           })

           articleViewModel.getPhoto(albumId).observe(this, Observer {
               photoAdapter.setData(it)
           })
       }
    }

    override fun attachView() {

    }

    override fun detachView() {

    }

}