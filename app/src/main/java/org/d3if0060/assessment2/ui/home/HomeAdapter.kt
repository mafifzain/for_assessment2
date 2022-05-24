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

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var entityList = emptyList<TokoEntity>()

    class MyViewHolder(dataView: View): RecyclerView.ViewHolder(dataView){  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_group_show, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = entityList[position]
        holder.itemView.namaTokoView_show.text = currentItem.namaToko.toString()
        holder.itemView.alamatTokoView_show.text = currentItem.alamatToko.toString()
        holder.itemView.deskripsiTokoView_show.text = currentItem.deskripsiToko.toString()

        holder.itemView.currRow.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToViewitemFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return entityList.size
    }

    fun setData(entity: List<TokoEntity>){
        this.entityList = entity
        notifyDataSetChanged()
    }
}
