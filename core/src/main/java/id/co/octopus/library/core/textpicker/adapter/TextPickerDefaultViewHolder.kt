package id.co.octopus.library.core.textpicker.adapter

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

internal class TextPickerDefaultViewHolder(
    private val view: View,
    private val textColorPickerDefault: Int,
    private val gravityValue: Int,
    private val textSizePickerDefault: Float,
    private val fontResIdTextPickerDefault: Int,
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
            if (fontResIdTextPickerDefault != 0) {
                val typeface = ResourcesCompat.getFont(context, fontResIdTextPickerDefault)
                this.typeface = typeface
            }
        }
        view.setOnClickListener {
            onClickItem.invoke(position)
        }
    }
}
