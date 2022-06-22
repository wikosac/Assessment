package org.d3if4401.assessment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HewanEntity::class], version = 1, exportSchema = false)
abstract class HewanDb : RoomDatabase() {

    abstract val dao: HewanDao

    companion object {
        @Volatile
        private var INSTANCE: HewanDb? = null
        fun getInstance(context: Context): HewanDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                instance =
                    Room.databaseBuilder(context.applicationContext, HewanDb::class.java, "bmi.db")
                        .fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
                return instance
            }
        }
    }
}