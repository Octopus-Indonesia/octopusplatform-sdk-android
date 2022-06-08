package id.co.octopus.library.core

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.co.octopus.library.core.CommonUtils.getCurrentPosition
import id.co.octopus.library.core.timepicker.HourAdapter
import id.co.octopus.library.core.timepicker.MinuteAdapter
import id.co.octopus.library.core.timepicker.TimePickerListener

object DialogUtils {

    fun showTimePickerBottomDialog(context: Context, titleDialog: String, listener: TimePickerListener) {
        var positionItemListHour: Int = 0
        var positionItemListMinute: Int = 0
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(R.layout.time_picker_dialog)

        val btnCancel = bottomSheetDialog.findViewById<TextView>(R.id.btnCancel)
        val btnSave = bottomSheetDialog.findViewById<TextView>(R.id.btnSave)
        val tvTitleDialog = bottomSheetDialog.findViewById<TextView>(R.id.tvTitleDialog)

        tvTitleDialog?.text = titleDialog

        val rvHour = bottomSheetDialog.findViewById<RecyclerView>(R.id.rvHour)
        val rvMinute = bottomSheetDialog.findViewById<RecyclerView>(R.id.rvMinute)
        val listHour =  mutableListOf<String>()
        for (number in 0..23){
            if (number < 10) {
                listHour.add("0$number")
            } else {
                listHour.add("$number")
            }
        }

        val listMinute =  mutableListOf<String>()
        for (number in 0..59){
            if (number < 10) {
                listMinute.add("0$number")
            } else {
                listMinute.add("$number")
            }
        }

        rvHour?.layoutManager =
            LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        val adapterHour = HourAdapter(
            listHour
        )
        rvHour?.adapter = adapterHour

        rvMinute?.layoutManager =
            LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        val adapterMinute = MinuteAdapter(
            listMinute
        )
        rvMinute?.adapter = adapterMinute

        val snapHelperHour: SnapHelper = LinearSnapHelper()
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelperHour.attachToRecyclerView(rvHour)
        snapHelper.attachToRecyclerView(rvMinute)

        rvHour?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                positionItemListHour = rvHour.getCurrentPosition()
            }
        })

        rvMinute?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                positionItemListMinute = rvMinute.getCurrentPosition()
            }
        })

        btnCancel?.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        btnSave?.setOnClickListener {
            listener.onPicked("${listHour[positionItemListHour % listHour.size]}:${listMinute[positionItemListMinute % listMinute.size]}")
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }
}