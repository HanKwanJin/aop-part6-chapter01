package com.han.aop_part6_chapter01.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.han.aop_part6_chapter01.data.db.dao.LocationDao
import com.han.aop_part6_chapter01.data.entity.LocationLatLngEntity

@Database(
    entities = [LocationLatLngEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase: RoomDatabase() {
    companion object{
        const val DB_NAME = "ApplicationDataBase.db"
    }
    abstract fun LocationDao(): LocationDao
}