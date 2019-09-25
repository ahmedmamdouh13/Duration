package com.ahmedmamdouh13.duration.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmedmamdouh13.duration.data.entity.Holiday

@Database(entities = [Holiday::class],version = 1,exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val dao:ArticleDao
}