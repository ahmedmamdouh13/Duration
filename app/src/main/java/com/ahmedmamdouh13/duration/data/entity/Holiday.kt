package com.ahmedmamdouh13.duration.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ahmedmamdouh13.duration.data.local.DateTypeConverter

@Entity
class Holiday {


    @PrimaryKey(autoGenerate = true)
    var primaryKey = 0

    @TypeConverters(DateTypeConverter::class)
    var date: Date? = null

    var description: String? = null
    var locations: String? = null
    var name: String? = null
    var states: String? = null

}
