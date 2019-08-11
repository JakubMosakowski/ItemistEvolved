package com.jakmos.itemistevolved.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jakmos.itemistevolved.data.converter.DateConverter
import com.jakmos.itemistevolved.data.converter.ItemListConverter
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity

@Database(
    entities = [ChecklistEntity::class],
    version = 1
)
@TypeConverters(ItemListConverter::class, DateConverter::class)
abstract class ItemistDatabase : RoomDatabase() {
    abstract fun checklistDao(): ChecklistDao

    companion object {
        @Volatile
        private var INSTANCE: ItemistDatabase? = null

        fun getDatabase(context: Context): ItemistDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    ItemistDatabase::class.java,
                    "itemist.db"
                ).build()
                INSTANCE = db

                return db
            }
        }
    }
}