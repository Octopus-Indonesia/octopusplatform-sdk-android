package id.co.octopus.library.core

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.co.octopus.library.core.CommonUtils.getCenterPosition
import id.co.octopus.library.core.timepicker.HourAdapter
import id.co.octopus.library.core.timepicker.MinuteAdapter
import id.co.octopus.library.core.timepicker.TimePickerListener


object DialogUtils {

    fun showTimePickerBottomDialog(
        context: Context,
        titleDialog: String,
        time: String,
        listener: TimePickerListener
    ) {
        val splitTime = time.split(":")
        val hour = splitTime[0]
        val minute = splitTime[1]

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

        var existingPositionHour = 0
        for (index in listHour.indices){
            if (listHour[index] == hour) {
                existingPositionHour = if (index != 0) {
                    index - 1
                } else {
                    listHour.size - 1
                }
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

        var existingPositionMinute = 0
        for (index in listMinute.indices){
            if (listMinute[index] == minute) {
                existingPositionMinute = if (index != 0) {
                    index - 1
                } else {
                    listMinute.size - 1
                }
            }
        }

        val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        val layoutManagerForHour = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )

        rvHour?.layoutManager = layoutManagerForHour
        rvHour?.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManagerForHour.orientation
            )
        )
        smoothScroller.targetPosition = existingPositionHour
        layoutManagerForHour.startSmoothScroll(smoothScroller)

        val adapterHour = HourAdapter(
            listHour
        )
        rvHour?.adapter = adapterHour

        val smoothScrollerMinute: SmoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        val layoutManagerForMinute = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )

        rvMinute?.layoutManager = layoutManagerForMinute
        rvMinute?.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManagerForMinute.orientation
            )
        )
        smoothScrollerMinute.targetPosition = existingPositionMinute
        layoutManagerForMinute.startSmoothScroll(smoothScrollerMinute)


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
                positionItemListHour = rvHour.getCenterPosition()
            }
        })

        rvMinute?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                positionItemListMinute = rvMinute.getCenterPosition()
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