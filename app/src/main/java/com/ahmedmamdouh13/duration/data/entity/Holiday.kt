package com.ahmedmamdouh13.duration.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ahmedmamdouh13.duration.data.local.DateTypeConverter

@Entity
class Holiday {


    @TypeConverters(DateTypeConverter::class)
    var date: Date = Date()

    var description: String? = ""
    var locations: String = ""
    @PrimaryKey
    var name: String = ""

}
