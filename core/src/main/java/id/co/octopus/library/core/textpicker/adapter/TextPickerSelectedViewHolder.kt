package id.co.octopus.library.core.textpicker.adapter

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class TextPickerSelectedViewHolder(
    private val view: View,
    private val textColorPickerSelected: Int,
    private val gravityValue: Int,
    private val textSizePickerSelected: Float
    ) : RecyclerView.ViewHolder(view) {

    fun bind(
        model: String,
        position: Int,
        onClickItem: (position: Int) -> Unit
    ) {
        (view as TextView).apply {
            text = model
            setTextColor(textColorPickerSelected)
            gravity = gravityValue
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePickerSelected)
        }
        view.setOnClickListener {
            onClickItem.invoke(position)
        }
    }
}
