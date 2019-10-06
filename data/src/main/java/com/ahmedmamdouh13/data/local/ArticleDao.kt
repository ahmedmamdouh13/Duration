package com.ahmedmamdouh13.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedmamdouh13.data.entity.Holiday

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticles(holiday: List<Holiday>):List<Long>

    @Query("SELECT * FROM holiday")
    suspend fun getAllArticle():List<Holiday>
}