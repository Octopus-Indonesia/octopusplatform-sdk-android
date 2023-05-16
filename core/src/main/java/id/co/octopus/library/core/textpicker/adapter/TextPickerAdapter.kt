package id.co.octopus.library.core.textpicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.octopus.library.core.R

internal class TextPickerAdapter(
    private var items: List<String>,
    private val onClickItem: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedIndex = -1
    private var textColorPickerSelected: Int = 0
    private var textColorPickerDefault: Int = 0
    private var textSizePickerSelected: Float = 0f
    private var textSizePickerDefault: Float = 0f
    private var gravityValue: Int = 0
    private var fontResIdTextPickerDefault: Int = 0
    private var fontResIdTextPickerSelected: Int = 0

    fun updateData(
        newItems: List<String>,
        textColorPickerSelected: Int,
        textColorPickerDefault: Int,
        gravityValue: Int,
        textSizePickerSelected: Float,
        textSizePickerDefault: Float,
        fontResIdTextPickerDefault: Int,
        fontResIdTextPickerSelected: Int,
    ) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(items, newItems))
        items = newItems
        this.textColorPickerSelected = textColorPickerSelected
        this.textColorPickerDefault = textColorPickerDefault
        this.gravityValue = gravityValue
        this.textSizePickerDefault = textSizePickerDefault
        this.textSizePickerSelected = textSizePickerSelected
        this.fontResIdTextPickerDefault = fontResIdTextPickerDefault
        this.fontResIdTextPickerSelected = fontResIdTextPickerSelected
        diffResult.dispatchUpdatesTo(this)
    }

    fun setSelectedIndex(index: Int) {
        selectedIndex = index
        notifyDataSetChanged()
    }

    fun setFontResIdTextPickerDefault(resId: Int) {
        fontResIdTextPickerDefault = resId
        notifyDataSetChanged()
    }

    fun setFontResIdTextPickerSelected(resId: Int) {
        fontResIdTextPickerSelected = resId
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SELECTED -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_text_picker_selected, parent, false)
                TextPickerSelectedViewHolder(
                    view, textColorPickerSelected,
                    gravityValue, textSizePickerSelected, fontResIdTextPickerSelected
                )
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_text_picker_default, parent, false)
                TextPickerDefaultViewHolder(
                    view, textColorPickerDefault,
                    gravityValue, textSizePickerDefault, fontResIdTextPickerDefault
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_SELECTED -> {
                val selectedViewHolder = holder as TextPickerSelectedViewHolder
                selectedViewHolder.bind(
                    items[position % items.size],
                    position,
                    onClickItem
                )
            }
            else -> {
                val otherViewHolder = holder as TextPickerDefaultViewHolder
                otherViewHolder.bind(
                    items[position % items.size],
                    position,
                    onClickItem
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == selectedIndex) {
            VIEW_TYPE_SELECTED
        } else {
            VIEW_TYPE_DEFAULT
        }
    }

    override fun getItemCount(): Int = items.size

    companion object {
        private const val VIEW_TYPE_SELECTED = 0
        private const val VIEW_TYPE_DEFAULT = 1
    }

    inner class MyDiffCallback(
        private val oldList: List<String>,
        private val newList: List<String>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

}
