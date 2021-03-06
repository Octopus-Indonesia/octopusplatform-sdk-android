package id.co.octopus.library.sdk

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.co.octopus.library.core.CommonUtils.disableButton
import id.co.octopus.library.core.CommonUtils.enableButton
import id.co.octopus.library.core.DialogUtils
import id.co.octopus.library.core.timepicker.TimePickerListener

class MainActivity : AppCompatActivity(), TimePickerListener {
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

}