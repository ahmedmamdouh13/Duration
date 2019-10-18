package com.ahmedmamdouh13.customcalendarview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.*


class CustomCalendarView {



    private var dayStartIndex: Int = 0
    private lateinit var s1: String
    private var year: Int = 0
    private var day: Int = 0
    private var month: Int = 0
    private lateinit var calendar: Calendar
    private lateinit var gridLayout: GridLayout
    private lateinit var datePicker: LinearLayout
    private var viewsList = mutableListOf<View>()
    private val dateUtil = DateUtil()
    lateinit var listener: callback

interface callback{
    fun onClickItem(date:String)
    fun onClickVisibleDate(date:String)
}

    fun init(mView: View, context: Context,callback: callback) {
        listener = callback
        datePicker = mView.findViewById(R.id.datepicker_calendarfragment)
        calendar = Calendar.getInstance()
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        year = calendar.get(Calendar.YEAR)
        val left = datePicker.findViewById<ImageView>(R.id.leftdirection_calendar_layout)
        val right = datePicker.findViewById<ImageView>(R.id.rightdirection_calendar_layout)

        moveMonth(context)
        left.setOnClickListener {
            //            gridLayout.removeAllViews()
            month -= 1
            moveMonth(context)
        }
        right.setOnClickListener {
            //            gridLayout.removeAllViews()
            month += 1
            moveMonth(context)
        }

    }

    private fun moveMonth(context: Context) {

        calendar.set(year, month, 1)


        day = calendar.get(Calendar.DAY_OF_MONTH)
        val day2 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)



        gridLayout = datePicker.findViewById(R.id.grid_gridlayout_calendar)

        gridLayout.removeAllViews()
        viewsList.clear()
        val mySplit = dateUtil.getVisibleDateDay(year, month, day).split(" ")
        s1 = mySplit[2]
        datePicker.findViewById<TextView>(R.id.date_calendar_layout).text = mySplit[0]
        datePicker.findViewById<TextView>(R.id.day_calendar_layout).text = mySplit[1]



        for (i in 1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {

            val dayItem = LayoutInflater.from(context).inflate(R.layout.item_calendar_layout, null)

            dayItem.setOnClickListener {

                listener.onClickVisibleDate("${dayItem.findViewById<TextView>(R.id.daynumber_item_calendar).text}-${mySplit[1]}-${mySplit[0]}")
                listener.onClickItem("${dayItem.findViewById<TextView>(R.id.daynumber_item_calendar).text}-$month-${mySplit[0]}")


            }


            dayItem.findViewById<TextView>(R.id.daynumber_item_calendar).text = "$i"
            if (i == 1) {
                dayStartIndex = when (s1) {
                    "Sun" -> 7
                    "Mon" -> 8
                    "Tue" -> 9
                    "Wed" -> 10
                    "Thu" -> 11
                    "Fri" -> 12
                    "Sat" -> 13
                    else -> 0
                }
                for (s in 1..dayStartIndex) {
                    val emptyItem = LayoutInflater.from(context).inflate(R.layout.item_calendar_layout, null)

                    emptyItem.findViewById<TextView>(R.id.daynumber_item_calendar).text = when (s) {
                        1 -> "S"
                        2 -> "M"
                        3 -> "T"
                        4 -> "W"
                        5 -> "T"
                        6 -> "F"
                        7 -> "S"
                        else -> ""
                    }
                    viewsList.add(emptyItem)
                    gridLayout.addView(emptyItem)
                }
                viewsList.add(dayItem)
                gridLayout.addView(dayItem)
            } else

                gridLayout.addView(dayItem)
            viewsList.add(dayItem)
            if (day2 == i && month == Calendar.getInstance().get(Calendar.MONTH)) {
                dayItem.findViewById<TextView>(R.id.daynumber_item_calendar).setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))

//                dayItem.findViewById<ImageView>(R.id.firstep_imageview).visibility = View.VISIBLE
            }


        }
//        if (eplist.isEmpty())
//        else
//            setShowDots(eplist,context)
    }

//    fun setShowDots(episode: List<Episode>, context: Context) {
//
////        if (eplist.isEmpty())
////            eplist.addAll(episode)
//
//        for (x in episode.indices) {
//            Timber.d("what the m ${episode[x].airdate} ${episode[x].showName}")
//            val split = episode[x].airdate!!.split("-")
////            var toString = ""
////
////            for (i in 7 until viewsList.size) {
////                val s = viewsList[i].findViewById<TextView>(R.id.daynumber_item_calendar).text.toString()
////
////                Timber.d(s)
////
////            }
////
////
////
////                if (dateUtil.isTheseDatesEqual(episode[x].airdate!!, calendar[Calendar.YEAR]
////                                , calendar[Calendar.MONTH], toString.toInt())) {
//
//            if (split[1].toInt() == calendar[Calendar.MONTH] + 1 && split[0].toInt() == calendar.get(Calendar.YEAR)) {
//                val imageView = ImageView(context)
//                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.white_small_circle))
//
//                if (!context.resources.getBoolean(R.bool.SCREEN_SIZE_300))
//                    imageView.layoutParams = ViewGroup.LayoutParams(15, 15)
//                else
//                    imageView.layoutParams = ViewGroup.LayoutParams(10, 10)
//
////                imageView.setPadding(1,1,1,1)
//
////                imageView.layoutParams.width = 5
//                imageView.setBackgroundResource(R.drawable.grey_small_circle)
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    imageView.imageTintList = ColorStateList.valueOf(episode[x].prColor)
////                    imageView.setBackgroundResource()
//
//                }
//
//                gridLayout.getChildAt(split[2].toInt() + dayStartIndex - 1).findViewById<GridLayout>(R.id.episodedots_gridlayout)
//                        .addView(imageView)
////                        .findViewById<ImageView>(R.id.firstep_imageview)
//                imageView.visibility = View.VISIBLE
//
//            }
//
//
////            }
//
//        }
//
//    }


}