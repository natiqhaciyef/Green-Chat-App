package com.natiqhaciyef.green_chat_app.view.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.green_chat_app.data.model.ChatModel
import com.natiqhaciyef.greenchatapp.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : BaseViewModel() {

    fun sendDataToFirestore(chatModel: ChatModel, message: String, receiverEmail: String) {
        val db = Firebase.firestore
        val auth = Firebase.auth.currentUser
        val list = chatModel.chat.toMutableList()

        viewModelScope.launch(Dispatchers.IO) {
            list.add("${auth!!.email} : $message")
            val listMap = hashMapOf<String, Any>()
            listMap["chat"] = list
            listMap["users"] = chatModel.users

            db.collection("Chat")
                .document("${auth.email} - $receiverEmail")
                .set(listMap)
                .addOnSuccessListener {
//                    deleteData(receiverEmail)
                }
                .addOnFailureListener {
                    println("Fail")
                }

        }
    }


    private fun deleteData(receiverEmail: String) {
        val db = Firebase.firestore
        val auth = Firebase.auth.currentUser
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("Chat")
                .document("${auth?.email} - $receiverEmail")
                .delete()
                .addOnSuccessListener {
                    println("Success")
                }
                .addOnFailureListener {
                    println("Fail")
                }
        }
    }

}