package com.ahmedmamdouh13.duration.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProjectEntity(@PrimaryKey(autoGenerate = true)
                         var key :Int = 0,var title: String,var startDate: String, var endDate: String)