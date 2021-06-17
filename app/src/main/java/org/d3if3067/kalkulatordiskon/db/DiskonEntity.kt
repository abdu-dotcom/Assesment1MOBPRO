package org.d3if3067.kalkulatordiskon.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_HitungDiskon")
class DiskonEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val biaya: Float,
    val diskon: Float,
)