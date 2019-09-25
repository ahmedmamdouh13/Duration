package com.ahmedmamdouh13.duration.data.local

import androidx.room.*
import com.ahmedmamdouh13.duration.data.entity.Holiday

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticles(holiday: List<Holiday>):List<Long>

    @Query("SELECT * FROM holiday")
    suspend fun getAllArticle():List<Holiday>
}