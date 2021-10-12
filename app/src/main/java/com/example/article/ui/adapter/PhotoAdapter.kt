package com.example.article.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.article.data.article.model.PhotoModel
import com.example.article.data.article.model.PostModel
import com.example.article.databinding.ItemPhotoBinding
import com.example.article.databinding.ItemPostBinding
import com.example.article.ui.ListPostActivity
import com.example.article.ui.PhotoDetailActivity
import com.example.article.ui.UserDetailActivity
import com.squareup.picasso.Picasso

class PhotoAdapter(
    val listData: ArrayList<PhotoModel>,
    val userDetailActivity: UserDetailActivity
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val itemBinding =
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = listData.size

    fun setData(newdata: List<PhotoModel>) {
        listData.clear()
        listData.addAll(newdata)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {
        fun bind() {

            val itemData = listData[position]

            Picasso.get().load(itemData.thumbnailUrl).into(itemPhotoBinding.imgPhoto)

            itemPhotoBinding.root.setOnClickListener {

                val intent = Intent(userDetailActivity, PhotoDetailActivity::class.java)
                intent.putExtra("title", itemData.title)
                intent.putExtra("url", itemData.url)
                userDetailActivity.startActivity(intent)

            }

        }
    }

}