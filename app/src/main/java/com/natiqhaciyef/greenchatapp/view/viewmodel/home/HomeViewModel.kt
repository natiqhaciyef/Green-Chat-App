package com.natiqhaciyef.greenchatapp.view.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.greenchatapp.data.model.ChatModel
import com.natiqhaciyef.greenchatapp.data.model.DataUserModel
import com.natiqhaciyef.greenchatapp.data.model.UserModel
import com.natiqhaciyef.greenchatapp.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    val chats = mutableStateOf<List<ChatModel>>(mutableListOf())
    val users = mutableStateOf<List<UserModel>>(mutableListOf())

    val list = mutableListOf<ChatModel>()
    val chartFlow = flow {
        val db = Firebase.firestore

        db.collection("Chat")
            .addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()
                    for (doc in docs) {
                        val users = doc["users"] as MutableList<String>
                        val chat = doc["chat"] as MutableList<String>

                        val chatModel = ChatModel(users, chat)
                        list.add(chatModel)
                    }
                }
            }
        emit(list)
    }

    init {
        viewModelScope.launch {
            chartFlow.collect()
        }
        getAllUsers()
        getAllChatsFromFirebase()
    }

    fun dataCollector(){
        viewModelScope.launch {
            chartFlow.collect()
        }
    }

    private fun getAllUsers() {
        val list = mutableListOf<UserModel>()
        val db = Firebase.firestore
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("Users")
                .addSnapshotListener { value, error ->
                    if (value != null && !value.isEmpty) {
                        val docs = value.documents
                        list.clear()
                        for (doc in docs) {
                            val name = doc["username"].toString()
                            val email = doc["email"].toString()
                            val phone = doc["phone"].toString()

                            val userModel = UserModel(
                                username = name,
                                phone = phone,
                                email = email,
                            )

                            list.add(userModel)
                        }
                        users.value = list
                    }
                }
        }
    }

    private fun getAllChatsFromFirebase() {
        val list = mutableListOf<ChatModel>()
        val db = Firebase.firestore
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("Chat")
                .addSnapshotListener { value, error ->
                    if (value != null && !value.isEmpty) {
                        val docs = value.documents
                        list.clear()
                        for (doc in docs) {
                            val users = doc["users"] as MutableList<String>
                            val chat = doc["chat"] as MutableList<String>

                            val chatModel = ChatModel(users, chat)
                            list.add(chatModel)
                        }
                        chats.value = list
                    }
                }
        }
    }

    fun recall() {
        getAllChatsFromFirebase()
    }

    fun filterChatsByEmail(email: String, chatModels: MutableList<ChatModel>)
            : MutableList<ChatModel> {
        val list = chatModels.filter {
            it.users.contains(email)
        }.toMutableList()

        return list
    }

    fun filterChatByUser(
        receiverEmail: String,
        filteredChats: MutableList<ChatModel>
    ): List<ChatModel> {
        val chat = filteredChats.filter {
            it.users.contains(receiverEmail)
        }

        return chat
    }

    fun getUserByEmail(email: String): UserModel {
        val dum = users.value.filter {
            it.email != email
        }

        return if (dum.isNotEmpty()) dum[0]
        else UserModel("", "", "")
    }
}