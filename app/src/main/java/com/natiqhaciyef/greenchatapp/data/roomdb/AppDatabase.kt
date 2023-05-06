package com.natiqhaciyef.greenchatapp.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.google.firebase.firestore.auth.User
import com.natiqhaciyef.greenchatapp.data.model.DataUserModel

@Database(entities = [DataUserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getUserDao():UserDao
}