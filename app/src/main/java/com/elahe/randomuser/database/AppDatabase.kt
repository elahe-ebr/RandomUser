package com.elahe.randomuser.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elahe.randomuser.data.Result

@Database(entities = [Result::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME = "db_user"
    }
}

fun createDataBaseInstance(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

}
