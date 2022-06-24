package org.d3if0060.assessment2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.TokoEntity
import kotlinx.android.synthetic.main.item_group_show.view.*
import org.d3if0060.assessment2.databinding.ItemGroupShowBinding

class HomeAdapter: ListAdapter<TokoEntity, HomeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<TokoEntity>() {
                override fun areItemsTheSame(
                    oldData: TokoEntity, newData: TokoEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: TokoEntity, newData: TokoEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    class MyViewHolder(private val binding: ItemGroupShowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tokoEntity: TokoEntity) = with(binding){
            namaTokoViewShow.text = tokoEntity.namaToko
            alamatTokoViewShow.text = tokoEntity.alamatToko
            deskripsiTokoViewShow.text = tokoEntity.deskripsiToko

            currRow.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToViewitemFragment(tokoEntity)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGroupShowBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
