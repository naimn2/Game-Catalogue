package com.muflihun.gamecatalogue.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muflihun.gamecatalogue.databinding.ItemDetailTagsBinding

class DetailTagsAdapter: RecyclerView.Adapter<DetailTagsAdapter.ViewHolder>() {
    var tags: List<String> = emptyList()
    inner class ViewHolder(private val binding: ItemDetailTagsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String) {
            binding.tvDetailTag.text = tag
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailTagsAdapter.ViewHolder {
        val binding = ItemDetailTagsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailTagsAdapter.ViewHolder, position: Int) {
        holder.bind(tags[position])
    }

    override fun getItemCount(): Int = tags.size
}