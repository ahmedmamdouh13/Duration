package com.ahmedmamdouh13.duration.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedmamdouh13.duration.data.entity.ProjectEntity

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProject(projectEntity: ProjectEntity):Long

    @Query("SELECT * FROM projectentity")
    suspend fun getProjects(): List<ProjectEntity>

}