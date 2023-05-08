package com.natiqhaciyef.greenchatapp.data.source

import com.natiqhaciyef.greenchatapp.data.model.DataUserModel
import com.natiqhaciyef.greenchatapp.data.roomdb.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource(var dao: UserDao) {

    suspend fun getAllUsers() = withContext(Dispatchers.IO){
        dao.getAllUsers()
    }

    suspend fun getUsersByEmail(email: String) = withContext(Dispatchers.IO){
        dao.getUsersByEmail(email)
    }

    suspend fun insertUser(dataUserModel: DataUserModel) = withContext(Dispatchers.IO){
        dao.insertUser(dataUserModel)
    }

    suspend fun deleteUser(dataUserModel: DataUserModel) = withContext(Dispatchers.IO){
        dao.deleteUser(dataUserModel)
    }

}