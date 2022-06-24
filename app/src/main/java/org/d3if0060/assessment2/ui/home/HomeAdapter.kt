package org.d3if0060.assessment2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.TokoEntity
import kotlinx.android.synthetic.main.item_group_show.view.*
import org.d3if0060.assessment2.databinding.ItemGroupShowBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private val data = mutableListOf<TokoEntity>()

    class MyViewHolder(private val binding: ItemGroupShowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tokoEntity: TokoEntity) = with(binding){
            namaTokoViewShow.text = tokoEntity.namaToko
            alamatTokoViewShow.text = tokoEntity.alamatToko
            deskripsiTokoViewShow.text = tokoEntity.deskripsiToko
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGroupShowBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
