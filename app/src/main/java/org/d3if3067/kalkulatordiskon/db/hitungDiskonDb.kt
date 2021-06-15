package org.d3if3067.kalkulatordiskon.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiskonEntity::class], version = 1, exportSchema = false)
abstract class hitungDiskonDb : RoomDatabase() {

    abstract val dao: DiskonDao

    companion object {

        @Volatile
        private var INSTANCE: hitungDiskonDb? = null

        fun getInstance(context: Context): hitungDiskonDb {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        hitungDiskonDb::class.java,
                        "tb_HitungDiskon"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}