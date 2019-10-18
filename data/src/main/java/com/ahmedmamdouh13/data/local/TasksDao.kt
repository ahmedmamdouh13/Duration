package com.ahmedmamdouh13.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedmamdouh13.data.entity.TaskEntity

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTask(taskEntity: TaskEntity):Long

    @Query("SELECT * FROM taskentity WHERE `key` = :id ")
    suspend fun getTasks(id:Int): List<TaskEntity>

}
