package com.example.article.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.article.data.article.model.PostModel
import com.example.article.databinding.ItemPostBinding
import com.example.article.ui.DetailPostActivity
import com.example.article.ui.ListPostActivity

class PostAdapter(
    val listData: ArrayList<PostModel>,
    val listPostActivity: ListPostActivity
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val itemBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = listData.size

    fun setData(newdata: PostModel) {
        listData.add(newdata)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemPostBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemPostBinding.root) {
        fun bind() {
            val itemData = listData[position]

            itemPostBinding.titlePost.text = itemData.title
            itemPostBinding.bodyPost.text = itemData.body
            itemPostBinding.namePost.text = itemData.username
            itemPostBinding.compPost.text = itemData.commpany

            itemPostBinding.root.setOnClickListener {

                val intent = Intent(listPostActivity, DetailPostActivity::class.java)
                intent.putExtra("title",itemData.title )
                intent.putExtra("body",itemData.body )
                intent.putExtra("postId",itemData.id )
                intent.putExtra("userId",itemData.userId )
                listPostActivity.startActivity(intent)

            }

        }
    }

}