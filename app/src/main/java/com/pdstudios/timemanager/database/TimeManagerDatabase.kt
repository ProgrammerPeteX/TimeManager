package com.pdstudios.timemanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdstudios.timemanager.database.to_do_list.ToDoListDao
import com.pdstudios.timemanager.database.to_do_list.ToDoListForm

@Database(entities =
[ToDoListForm::class],
    version = 1, exportSchema = false)

abstract class TimeManagerDatabase: RoomDatabase() {

    abstract val toDoListDao: ToDoListDao

    companion object {
        @Volatile
        private var INSTANCE: TimeManagerDatabase? = null

        fun getInstance(context: Context): TimeManagerDatabase {
            synchronized(   this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TimeManagerDatabase::class.java,
                        "time_manager_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}