package com.brokenbrains.fitness.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activity")
    fun getAll(): Flow<List<ActivityModel>>

    @Query("SELECT * FROM activity WHERE id = :id")
    fun getById(id: Int): Flow<ActivityModel>

    @Query("SELECT * FROM activity WHERE activity_type = :type")
    fun getByType(type: ActivityType): Flow<List<ActivityModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: ActivityModel)

    @Update
    fun update(model: ActivityModel)

}