package com.example.article.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.article.base.BaseActivity
import com.example.article.data.article.model.PostModel
import com.example.article.databinding.ActivityListBinding
import com.example.article.ui.adapter.PostAdapter
import com.example.article.ui.viewmodel.ArticleViewModel
import com.example.article.utils.launch
import org.koin.android.ext.android.inject

class ListPostActivity : BaseActivity(){

    private val articleViewModel : ArticleViewModel by inject()
    private lateinit var binding : ActivityListBinding
    private lateinit var postAdapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecycleView()
        getData()

    }

    private fun initRecycleView(){
        postAdapter =  PostAdapter(arrayListOf(), this)
        binding.recList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recList.adapter = postAdapter
    }

    private fun getData(){
        launch {
            articleViewModel.getPost().observe(this, Observer {
                postAdapter.listData.clear()
                it.forEach {
                    launch {
                        getUserData(it)
                    }
                }
            })
        }
    }

    private fun getUserData(postModel: PostModel) {
        launch {
          articleViewModel.getUsers(postModel.userId).observe(this, Observer {
              postModel.username = it.username
              postModel.commpany = it.company.name
              postAdapter.setData(postModel)
          })
        }
    }

    override fun attachView() {

    }

    override fun detachView() {

    }
}