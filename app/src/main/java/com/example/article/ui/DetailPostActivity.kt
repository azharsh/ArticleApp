package com.example.article.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.article.base.BaseActivity
import com.example.article.databinding.ActicityDetailBinding
import com.example.article.databinding.ActivityListBinding
import com.example.article.ui.adapter.CommentAdapter

import com.example.article.ui.viewmodel.ArticleViewModel
import com.example.article.utils.launch
import org.koin.android.ext.android.inject

class DetailPostActivity : BaseActivity() {

    private val articleViewModel : ArticleViewModel by inject()
    private lateinit var binding : ActicityDetailBinding
    private lateinit var commentAdapter: CommentAdapter

    private var userId = 0
    private var postId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActicityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userId = intent.getIntExtra("userId", 0)
        postId = intent.getIntExtra("postId", 0)

        initRecycleView()
        getData()
        initListener()
    }

    private fun initListener(){
        binding.usernameDetail.setOnClickListener {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra("userId",userId)
            startActivity(intent)
        }
    }

    private fun initRecycleView(){
        commentAdapter = CommentAdapter(arrayListOf(), this)
        binding.recComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recComment.adapter = commentAdapter
    }

    private fun getData(){

        binding.titleDetail.text = intent.getStringExtra("title")
        binding.descDetail.text = intent.getStringExtra("body")

        launch {
            articleViewModel.geCommment(postId).observe(this, Observer {
                commentAdapter.setData(it)
            })

            articleViewModel.getUsers(userId).observe(this, Observer {
                binding.usernameDetail.text = it.name
            })
        }


    }

    override fun attachView() {

    }

    override fun detachView() {

    }
}