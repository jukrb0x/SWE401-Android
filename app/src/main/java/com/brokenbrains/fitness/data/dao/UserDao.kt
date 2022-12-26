package com.brokenbrains.fitness.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brokenbrains.fitness.data.model.user.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun getModel(uid: Int): Flow<UserModel>

    // insert new data
    // update existing data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModel(model: UserModel)

    @Query("DELETE FROM user")
    suspend fun clearAll() // logout
}