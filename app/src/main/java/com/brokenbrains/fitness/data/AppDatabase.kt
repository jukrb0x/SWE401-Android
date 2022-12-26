package com.brokenbrains.fitness.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brokenbrains.fitness.data.dao.UserDao
import com.brokenbrains.fitness.data.model.user.UserModel

// TODO
@Database(
    entities = [
        UserModel::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    // DAOs
      abstract fun userDao(): UserDao

    // the app database singleton will be provided by DI
}