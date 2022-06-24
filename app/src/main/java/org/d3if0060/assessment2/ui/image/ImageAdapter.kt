package org.d3if0060.assessment2.ui.image

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.TokoImage
import org.d3if0060.assessment2.databinding.ItemFimageBinding
import org.d3if0060.assessment2.network.TokoApi

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val data = mutableListOf<TokoImage>()

    class ViewHolder(private val binding: ItemFimageBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(tokoImage: TokoImage) = with(binding){
            textView.text = tokoImage.nama
            Glide.with(imageToko.context)
                .load(TokoApi.getTokoUrl(tokoImage.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageToko)
        }
    }

    fun updateData(newData: List<TokoImage>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFimageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}