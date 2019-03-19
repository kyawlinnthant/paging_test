package com.kyawlinnthant.pagingtest.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyawlinnthant.pagingtest.model.db.News

@Database(entities = [News::class],version = 1,exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao():NewsDao
}