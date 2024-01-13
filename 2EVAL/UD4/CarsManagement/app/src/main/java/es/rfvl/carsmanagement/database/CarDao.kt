package es.rfvl.carsmanagement.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {

    @Query("Select * from auto")
    suspend fun getAll(): MutableList<Auto>

    @Insert
    suspend fun insert(auto: Auto)

    @Update
    suspend fun update(auto: Auto)

    @Delete
    suspend fun delete(auto: Auto)
    
}