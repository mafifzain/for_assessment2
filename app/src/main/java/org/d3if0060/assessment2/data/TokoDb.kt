package org.d3if0060.assessment2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TokoEntity::class], version = 1, exportSchema = false)
abstract class TokoDb: RoomDatabase() {
    abstract fun tokoDao(): TokoDao

    companion object {
        @Volatile
        private var INSTANCE: TokoDb? = null

        fun getDataDb(context: Context): TokoDb {
            val instance = INSTANCE
            if (instance != null){
                return instance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TokoDb::class.java,
                    "forassessment_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}