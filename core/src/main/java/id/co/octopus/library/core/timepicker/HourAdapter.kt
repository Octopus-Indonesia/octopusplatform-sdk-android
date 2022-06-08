package id.co.octopus.library.core.timepicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.octopus.library.core.databinding.ItemListHourBinding

class HourAdapter(
    private var itemList: List<String>
) : RecyclerView.Adapter<HourAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListHourBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.tvText.text = itemList[position % itemList.size]
    }

    class ItemViewHolder(val binding: ItemListHourBinding) :
        RecyclerView.ViewHolder(binding.root)
}