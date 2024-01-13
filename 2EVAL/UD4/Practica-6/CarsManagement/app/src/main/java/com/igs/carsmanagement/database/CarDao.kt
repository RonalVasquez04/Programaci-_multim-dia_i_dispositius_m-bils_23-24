package com.igs.carsmanagement.database

import androidx.room.*

@Dao
interface CarDao {

    @Query("SELECT * FROM car")
    fun getAll(): MutableList<Car>

    @Query("Delete * From car where id = :id")
    fun deleteCarById(id: Int): Car

    @Query("Select * FROM car where model = :model")
    fun findByModel(model: String): Car

    @Insert
    fun insert(car: Car)

    @Update
    fun update(car: Car)

    @Delete
    fun delete(car: Car)


}