package com.ahmedmamdouh13.customcalendarview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar


/**
 * Created by ahmedmamdouh13 on 11/10/17.
 */

class CalendarFragment : DialogFragment(),CustomCalendarView.callback {

    private var colorBackground: Int = 0
    private var text: String = "Calendar"
    lateinit var mView: View

    lateinit var datePicker: LinearLayout
    lateinit var mCalendarInterface: CalendarInterface

    lateinit var customCalendarView: CustomCalendarView


    fun setCalendarInterface(calendarInterface: CalendarInterface) {
        this.mCalendarInterface = calendarInterface
    }

    interface CalendarInterface {
        fun dateCallBack(date:String)
        fun visibleDateCallBack(date:String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.arguments?.getString("msg")?.let {
            text = it
        }

        this.arguments?.getInt("color")?.let {
            colorBackground = it
        }

        this.setStyle(STYLE_NO_FRAME, R.style.transparentTheme)
    }

    override fun onStart() {
        super.onStart()

        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            this.dialog?.window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
//        this.dialog?.window!!.setBackgroundDrawableResource(colorBackground)


        mView = inflater.inflate(R.layout.fragment_calendar, container, false)
        datePicker = mView.findViewById(R.id.datepicker_calendarfragment)
        customCalendarView = CustomCalendarView()

        customCalendarView.init(mView, context!!,this)
        Snackbar.make(mView.findViewById(R.id.container_fragmentcalendar),text,Snackbar.LENGTH_INDEFINITE).show()

        return mView
    }

    override fun onClickVisibleDate(date: String) {
        mCalendarInterface.visibleDateCallBack(date)
    }

    override fun onClickItem(date: String) {
        println(date)
        mCalendarInterface.dateCallBack(date)
        dismiss()
    }

}
