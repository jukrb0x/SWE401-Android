package com.brokenbrains.fitness.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brokenbrains.fitness.model.user.UserModel

// TODO
@Database(
    entities = [
        UserModel::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
//    abstract fun userDao():UserDao

    // the app database singleton will be provided in DI
}