package com.natiqhaciyef.greenchatapp.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natiqhaciyef.greenchatapp.data.model.DataUserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<DataUserModel>

    @Query("SELECT * FROM user_table WHERE email = :email")
    suspend fun getUsersByEmail(email: String): List<DataUserModel?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(dataUserModel: DataUserModel)

    @Delete
    suspend fun deleteUser(dataUserModel: DataUserModel)

}