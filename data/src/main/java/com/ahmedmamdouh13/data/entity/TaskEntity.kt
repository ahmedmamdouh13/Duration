package com.ahmedmamdouh13.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(@PrimaryKey(autoGenerate = true)
                          var key: Int, var title: String, var tag: String,var projectTitle:String)