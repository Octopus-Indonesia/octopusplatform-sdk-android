package id.co.octopus.library.core.textpicker

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import id.co.octopus.library.core.R
import id.co.octopus.library.core.databinding.ViewTextPickerBinding
import id.co.octopus.library.core.textpicker.adapter.TextPickerAdapter

class TextPickerView : BaseTextPickerView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        R.attr.textPickerViewStyle
    )
    constructor(context: Context, attributeSet: AttributeSet?, defAttributeSet: Int) : super(
        context,
        attributeSet,
        defAttributeSet
    ) {
        setCustomAttributes(attributeSet, defAttributeSet, R.style.Widget_TextPickerView)
        initView()
    }

    private val binding: ViewTextPickerBinding = ViewTextPickerBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private val rvData: RecyclerView by lazy { binding.rvData }

    private var textColorPickerSelected: Int = ContextCompat.getColor(context, R.color.text_color_picker_selected)
    private var textColorPickerDefault: Int = ContextCompat.getColor(context, R.color.text_color_picker_default)
    private var gravityValue = Gravity.START
    private var textSizePickerSelected: Float = context.resources.getDimension(R.dimen.text_picker_view_selected_size)
    private var textSizePickerDefault: Float = context.resources.getDimension(R.dimen.text_picker_view_default_size)
    private var posSelected: Int = 0
    private var fontResIdTextPickerSelected: Int = 0
    private var fontResIdTextPickerDefault: Int = 0

    private var list = emptyList<String>()
    private val snapHelper = LinearSnapHelper()

    private val listAdapter = TextPickerAdapter(
        list
    ) { position ->
        onItemClicked(position, rvData)
    }

    private fun setCustomAttributes(
        attributeSet: AttributeSet?,
        defAttributeSet: Int,
        defStyle: Int
    ) {
        context.obtainStyledAttributes(
            attributeSet,
            R.styleable.TextPickerView,
            defAttributeSet,
            defStyle
        ).apply {
            val drawableResId = this.getResourceId(R.styleable.TextPickerView_backgroundCenterOfView, 0)
            if (drawableResId != 0) {
                binding.viewCenter.setBackgroundResource(drawableResId)
            }
            textColorPickerSelected = this.getColor(R.styleable.TextPickerView_textColorPickerSelected, textColorPickerSelected)
            textColorPickerDefault = this.getColor(R.styleable.TextPickerView_textColorPickerDefault, textColorPickerDefault)
            gravityValue = this.getInt(R.styleable.TextPickerView_textGravityOfPicker, Gravity.START)
            textSizePickerSelected = this.getDimension(R.styleable.TextPickerView_textSizePickerSelected, textSizePickerSelected)
            textSizePickerDefault = this.getDimension(R.styleable.TextPickerView_textSizePickerDefault, textSizePickerDefault)
            fontResIdTextPickerDefault = getResourceId(R.styleable.TextPickerView_fontFamilyTextPickerDefault, 0)
            fontResIdTextPickerSelected = getResourceId(R.styleable.TextPickerView_fontFamilyTextPickerSelected, 0)
        }.recycle()
    }

    fun getSelectedIndex(): Int {
        return posSelected
    }

    fun setSelectedIndex(selectedIndex: Int) {
        posSelected = selectedIndex
    }

    fun getTextPicked(): String {
        var result = ""
        list.forEachIndexed { index, value ->
            if (snapHelper.getSnapPosition(rvData) == index) {
                result = value
            }
        }
        return result
    }

    private fun initView() {
        rvData.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = SlowLinearLayoutManager(context, rvData)
            layoutManager?.scrollToPosition(posSelected)
            snapHelper.attachToRecyclerView(this)
            listAdapter.setSelectedIndex(posSelected)
            addListeners { position ->
                posSelected = position
                listAdapter.setSelectedIndex(position)
            }
        }
    }

    fun setCustomList(
        list: List<String>
    ) {
        this.list = list
        listAdapter.updateData(
            list,
            textColorPickerSelected,
            textColorPickerDefault,
            gravityValue,
            textSizePickerSelected,
            textSizePickerDefault,
            fontResIdTextPickerDefault,
            fontResIdTextPickerSelected
        )
        rvData.scrollToPosition(posSelected)
        listAdapter.setSelectedIndex(posSelected)
    }

    fun setTextSizePickerSelected(textSize: Float) {
        textSizePickerSelected = textSize
    }

    fun setTextSizePickerDefault(textSize: Float) {
        textSizePickerDefault = textSize
    }

    fun setTextColorPickerSelected(color: Int) {
        textColorPickerSelected = color
    }

    fun setTextColorPickerDefault(color: Int) {
        textColorPickerDefault = color
    }

    fun setFontResIdTextPickerDefault(resId: Int) {
        fontResIdTextPickerDefault = resId
        listAdapter.setFontResIdTextPickerDefault(fontResIdTextPickerDefault)
    }

    fun setFontResIdTextPickerSelected(resId: Int) {
        fontResIdTextPickerSelected = resId
        listAdapter.setFontResIdTextPickerSelected(fontResIdTextPickerSelected)
    }

    override fun fadeView(view: RecyclerView, duration: Long, alpha: Float) {
        super.startFadeAnimation(
            listOf(binding.viewTopShade, binding.viewBottomShade),
            duration, alpha
        )
    }

}