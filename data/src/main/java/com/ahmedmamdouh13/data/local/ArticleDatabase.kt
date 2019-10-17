package com.ahmedmamdouh13.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmedmamdouh13.data.entity.Holiday
import com.ahmedmamdouh13.data.entity.ProjectEntity
import com.ahmedmamdouh13.data.entity.TaskEntity

@Database(entities = [Holiday::class, ProjectEntity::class,TaskEntity::class],version = 8,exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val dao: ArticleDao
    abstract val projDao: ProjectDao
    abstract val tasksDao: TasksDao
}