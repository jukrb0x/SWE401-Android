package com.brokenbrains.fitness.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserModel {
    @PrimaryKey
    @ColumnInfo(name = "uid")
    var uid: Int? = null

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "password")
    var password: String? = null

    @ColumnInfo(name = "age")
    var age: Int? = null

    @ColumnInfo(name = "weight")
    var weight: Int? = null

    @ColumnInfo(name = "height")
    var height: Int? = null

    @ColumnInfo(name = "bloodType")
    var bloodType: String? = null
}