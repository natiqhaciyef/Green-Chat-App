package com.natiqhaciyef.greenchatapp.domain.repository

import com.natiqhaciyef.greenchatapp.data.model.DataUserModel
import com.natiqhaciyef.greenchatapp.data.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(var ds: UserDataSource) {

    suspend fun getAllUsers() =
        ds.getAllUsers()


    suspend fun getUsersByEmail(email: String) =
        ds.getUsersByEmail(email)


    suspend fun insertUser(dataUserModel: DataUserModel) =
        ds.insertUser(dataUserModel)


    suspend fun deleteUser(dataUserModel: DataUserModel) =
        ds.deleteUser(dataUserModel)

}