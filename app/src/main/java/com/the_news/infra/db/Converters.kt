package com.the_news.infra.db

import androidx.room.TypeConverter
import com.the_news.data.model.Source

class Converters {

    @TypeConverter
    fun fromSources(sources: Source): String {
        return sources.name ?: ""
    }

    @TypeConverter
    fun toSources(name: String): Source {
        return Source(name, name)
    }

}
