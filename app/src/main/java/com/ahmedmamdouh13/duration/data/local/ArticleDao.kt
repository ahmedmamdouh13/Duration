package com.ahmedmamdouh13.duration.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ahmedmamdouh13.duration.data.entity.Holiday

@Dao
interface ArticleDao {

    @Insert
    suspend fun insertAllArticles(holiday: List<Holiday>):List<Long>

    @Query("SELECT * FROM holiday")
    suspend fun getAllArticle():List<Holiday>
}