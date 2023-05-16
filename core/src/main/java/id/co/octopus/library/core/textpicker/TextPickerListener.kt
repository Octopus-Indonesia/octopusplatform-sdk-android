package id.co.octopus.library.core.textpicker

interface TextPickerListener {
    fun onTextPicked(
        textSelected: String,
        tag: String?,
        selectedIndex: Int? = 0
    )
}