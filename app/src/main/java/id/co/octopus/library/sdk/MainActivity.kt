package id.co.octopus.library.sdk

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

    }

    private fun showDialog() {
        DialogUtils.showTimePickerBottomDialog(this@MainActivity, "Pilih Jam Tutup", this@MainActivity)
    }

    /*
    * Get time who picked in bottom dialog
    * */
    override fun onPicked(timePicked: String) {
        val tvTimePicked = findViewById<TextView>(R.id.tvTimePicked)
        tvTimePicked.text = timePicked
    }

}