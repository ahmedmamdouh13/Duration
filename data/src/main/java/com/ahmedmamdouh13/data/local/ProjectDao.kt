package com.ahmedmamdouh13.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedmamdouh13.data.entity.ProjectEntity

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProject(projectEntity: ProjectEntity):Long

    @Query("SELECT * FROM projectentity")
    suspend fun getProjects(): List<ProjectEntity>

    @Query("SELECT * FROM projectentity WHERE `key` = :id")
    suspend fun getProjectById(id: Int): ProjectEntity

}
