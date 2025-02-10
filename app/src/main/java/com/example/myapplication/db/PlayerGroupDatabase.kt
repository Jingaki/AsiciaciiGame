package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.model.PlayerGroup

@Database(entities = [PlayerGroup::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlayerGroupDatabase : RoomDatabase() {
    abstract fun playerGroupDao() : PlayerGroupDao

    companion object {

        @Volatile
        private var INSTANCE: PlayerGroupDatabase? = null
        fun getDatabase(context: Context) : PlayerGroupDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, PlayerGroupDatabase::class.java, "appDatabase").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}