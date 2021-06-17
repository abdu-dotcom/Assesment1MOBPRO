package org.d3if3067.kalkulatordiskon.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiskonDao {

    @Insert
    fun insert(hitungDiskon: DiskonEntity)

    @Query("Select * From tb_HitungDiskon order by id DESC")
    fun getLastHitung(): LiveData<List<DiskonEntity?>>

    @Query("Delete From tb_HitungDiskon")
    fun deleteAll();

}