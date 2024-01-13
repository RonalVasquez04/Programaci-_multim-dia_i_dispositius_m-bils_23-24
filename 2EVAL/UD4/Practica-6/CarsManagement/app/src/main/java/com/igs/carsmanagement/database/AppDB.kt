package com.igs.carsmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Car::class],
    version = 1
)
abstract class AppDB : RoomDatabase(){
    abstract fun carDao(): CarDao


}