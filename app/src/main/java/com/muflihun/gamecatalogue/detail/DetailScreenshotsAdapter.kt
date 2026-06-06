package com.muflihun.gamecatalogue.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muflihun.gamecatalogue.databinding.DetailScreenshotItemBinding

class DetailScreenshotsAdapter : RecyclerView.Adapter<DetailScreenshotsAdapter.ViewHolder>() {
    var screenshots: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DetailScreenshotItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(screenshots[position])
    }

    override fun getItemCount(): Int = screenshots.size

    inner class ViewHolder(private val binding: DetailScreenshotItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            Glide.with(binding.root.context)
                .load(image)
                .into(binding.ivDetailScreenshot)
        }
    }
}