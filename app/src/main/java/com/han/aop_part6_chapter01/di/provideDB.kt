package com.han.aop_part6_chapter01.di

import android.content.Context
import androidx.room.Room
import com.han.aop_part6_chapter01.data.db.ApplicationDatabase

fun provideDB(context: Context): ApplicationDatabase =
    Room.databaseBuilder(
        context,
        ApplicationDatabase::class.java,
        ApplicationDatabase.DB_NAME).build()

fun provideLocationDao(database: ApplicationDatabase) = database.LocationDao()