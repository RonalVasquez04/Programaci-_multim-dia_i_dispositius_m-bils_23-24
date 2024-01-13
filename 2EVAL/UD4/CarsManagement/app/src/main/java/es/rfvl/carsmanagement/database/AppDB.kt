package es.rfvl.carsmanagement.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Auto::class],
    version = 1

)

abstract class AppDB: RoomDatabase() {
    abstract fun carDao() : CarDao
}