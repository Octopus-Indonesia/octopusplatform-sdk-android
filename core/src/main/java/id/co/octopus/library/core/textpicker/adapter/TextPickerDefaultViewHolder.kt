package id.co.octopus.library.core.textpicker.adapter

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class TextPickerDefaultViewHolder(
    private val view: View,
    private val textColorPickerDefault: Int,
    private val gravityValue: Int,
    private val textSizePickerDefault: Float,
    ) : RecyclerView.ViewHolder(view) {

    fun bind(
        model: String,
        position: Int,
        onClickItem: (position: Int) -> Unit
    ) {
        (view as TextView).apply {
            text = model
            setTextColor(textColorPickerDefault)
            gravity = gravityValue
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePickerDefault)
        }
        view.setOnClickListener {
            onClickItem.invoke(position)
        }
    }
}
