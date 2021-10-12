package com.example.article.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.article.data.article.model.CommentModel
import com.example.article.data.article.model.PhotoModel
import com.example.article.databinding.ItemCommentBinding
import com.example.article.databinding.ItemPhotoBinding
import com.example.article.ui.DetailPostActivity
import com.example.article.ui.ListPostActivity

class CommentAdapter(
    val listData: ArrayList<CommentModel>,
    val detailPostActivity: DetailPostActivity
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val itemBinding =
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = listData.size

    fun setData(newdata: List<CommentModel>) {
        listData.clear()
        listData.addAll(newdata)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemCommentBinding: ItemCommentBinding) :
        RecyclerView.ViewHolder(itemCommentBinding.root) {
        fun bind() {

            val itemData = listData[position]
            itemCommentBinding.authorComment.text = itemData.name
            itemCommentBinding.bodyComment.text = itemData.body

        }
    }

}