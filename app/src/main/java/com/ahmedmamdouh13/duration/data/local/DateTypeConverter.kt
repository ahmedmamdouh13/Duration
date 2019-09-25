package com.ahmedmamdouh13.duration.data.local

import androidx.room.TypeConverter
import com.ahmedmamdouh13.duration.data.entity.Date
import com.ahmedmamdouh13.duration.data.entity.Datetime

class DateTypeConverter {

    @TypeConverter
    fun toString(date: Date):String = date.let {
        val datetime = it.datetime!!
        "${datetime.day}/${datetime.month}/${datetime.year}"
    }
    @TypeConverter
    fun toDate(string: String):Date = string.let {
        val date = Date()
        date.datetime = Datetime()
        val split = it.split("/")
        date.datetime!!.day = split[0].toLong()
        date.datetime!!.month = split[1].toLong()
        date.datetime!!.year = split[2].toLong()
        date
    }
}