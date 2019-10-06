package com.ahmedmamdouh13.duration.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmedmamdouh13.duration.data.entity.Holiday
import com.ahmedmamdouh13.duration.data.entity.ProjectEntity

@Database(entities = [Holiday::class,ProjectEntity::class],version = 6,exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val dao:ArticleDao
    abstract val projDao:ProjectDao
}