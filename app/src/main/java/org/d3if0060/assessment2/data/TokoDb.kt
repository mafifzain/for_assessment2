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
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TokoDb::class.java,
                    "item_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}