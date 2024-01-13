package es.rfvl.carsmanagement.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("auto")
data class Auto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("brand")
    val brand: String,

    @ColumnInfo("model")
    val model: String = ""
)
