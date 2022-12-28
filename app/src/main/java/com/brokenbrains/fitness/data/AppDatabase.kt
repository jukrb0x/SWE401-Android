package com.brokenbrains.fitness.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brokenbrains.fitness.data.dao.ActivityDao
import com.brokenbrains.fitness.data.dao.UserDao
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.user.UserModel

// TODO
@TypeConverters(Converters::class)
@Database(
    entities = [
        UserModel::class,
        ActivityModel::class
    ], version = 3
)
abstract class AppDatabase : RoomDatabase() {
    // DAOs
    abstract fun userDao(): UserDao

    abstract fun activityDao(): ActivityDao

    // the app database singleton will be provided by DI
}