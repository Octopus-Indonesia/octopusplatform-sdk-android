package id.co.octopus.library.sdk

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.co.octopus.library.core.CommonUtils.disableButton
import id.co.octopus.library.core.CommonUtils.enableButton
import id.co.octopus.library.core.DialogUtils
import id.co.octopus.library.core.textpicker.TextPickerListener
import id.co.octopus.library.core.textpicker.TextPickerView
import id.co.octopus.library.core.timepicker.TimePickerListener

class MainActivity : AppCompatActivity(), TimePickerListener, TextPickerListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTimePickerDialog = findViewById<Button>(R.id.btnTimePickerDialog)
        btnTimePickerDialog.setOnClickListener {
            showDialog()
        }

        val btnSampleButtonState = findViewById<Button>(R.id.btnSampleButtonState)
        btnSampleButtonState.setOnClickListener {
            btnSampleButtonState.disableButton()
            showDialog()
        }


        val textPickerView = findViewById<TextPickerView>(R.id.textPickerView)
        val btnGetValueFromTextPicker = findViewById<Button>(R.id.btnGetValueFromTextPicker)
        val btnShowPopupTextPicker = findViewById<Button>(R.id.btnShowPopupTextPicker)

        val list = listOf("Item 1", "Item 2", "Item 3 Item 3 Item 3 Item 3 Item 3 Item 3 Item 3 Item 3")
        textPickerView.setCustomList(list)

        btnGetValueFromTextPicker.setOnClickListener {
            Toast.makeText(
                this,
                textPickerView.getTextPicked(),
                Toast.LENGTH_SHORT
            ).show()
        }

        val listForDialog = listOf("1 item", "2 item", "3 item", "4 item")

        btnShowPopupTextPicker.setOnClickListener {
            DialogUtils.showTextPickerBottomDialog(
                context = this@MainActivity,
                dialogTitle = "Choose Gender",
                list = listForDialog,
                listener = this@MainActivity,
                tag = "tag1",
                textSizePickerSelected = resources.getDimension(R.dimen.text_picker_view_selected_size),
                textSizePickerDefault = resources.getDimension(R.dimen.text_picker_view_default_size)
            )
        }
    }

    private fun showDialog() {
        val tvTimePicked = findViewById<TextView>(R.id.tvTimePicked)
        DialogUtils.showTimePickerBottomDialog(
            this@MainActivity,
            "Pilih Jam Tutup",
            tvTimePicked.text.toString(),
            this@MainActivity)
    }

    /*
    * Get time who picked in bottom dialog
    * */
    override fun onPicked(timePicked: String) {
        val tvTimePicked = findViewById<TextView>(R.id.tvTimePicked)
        tvTimePicked.text = timePicked
        val btnSampleButtonState = findViewById<Button>(R.id.btnSampleButtonState)
        btnSampleButtonState.enableButton()
    }

    /*
    * Get text who selected in bottom dialog
    * */
    override fun onTextPicked(textSelected: String, tag: String?) {
        when(tag) {
            "tag1" -> {
                Toast.makeText(
                    this,
                    "tag1: $textSelected",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                Toast.makeText(
                    this,
                    "default: $textSelected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}