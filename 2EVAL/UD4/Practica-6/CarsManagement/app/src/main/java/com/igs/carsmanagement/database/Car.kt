package com.igs.carsmanagement.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("car")
data class Car (
    @PrimaryKey(autoGenerate = true)

    val id: Int = 0,
    @ColumnInfo("brend")
    val Brend: String,

    @ColumnInfo("model")
    val Model: String
    )