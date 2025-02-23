package com.the_news.infra.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.the_news.data.model.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO
}