package com.natiqhaciyef.greenchatapp.view.viewmodel.registration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.greenchatapp.view.viewmodel.BaseViewModel
import com.natiqhaciyef.greenchatapp.data.model.DataUserModel
import com.natiqhaciyef.greenchatapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    var userRepo: UserRepository
) : BaseViewModel() {

    val dums = flow {
        val list = userRepo.getAllUsers().toMutableList()
        emit(list)
    }
    var dumsFromFirebase = mutableStateOf<List<DataUserModel>>(mutableListOf())

    init {
        collectUsers()
        getAllUsersFromFirebase()
    }

    fun collectUsers(): List<DataUserModel> {
        var list = mutableListOf<DataUserModel>()
        viewModelScope.launch(Dispatchers.Main) {
            dums.collectLatest { dumList ->
                for (element in dumList) {
                    list.add(element)
                }
            }
        }
        return list
    }

    //rm -rf .git

    fun insertUserModel(userModel: DataUserModel) {
        viewModelScope.launch(Dispatchers.Main) {
            userRepo.insertUser(userModel)
        }
    }

    fun deleteUserModel(userModel: DataUserModel) {
        viewModelScope.launch(Dispatchers.Main) {
            userRepo.deleteUser(userModel)
        }
    }

    fun getUserByEmail(email: String, userModel: DataUserModel) {
        viewModelScope.launch(Dispatchers.Main) {
            val user = userRepo.getUsersByEmail(email)
            if (user == null) {
                insertUserModel(userModel)
            }
        }
    }

    private fun sendUserFirestore(dataUserModel: DataUserModel) {
        val firestore = Firebase.firestore
        viewModelScope.launch(Dispatchers.IO) {
            val map = hashMapOf<String, Any>()
            map["username"] = dataUserModel.username
            map["email"] = dataUserModel.email
            map["phone"] = dataUserModel.phone
            map["password"] = dataUserModel.password

            firestore.collection("Users")
                .document(dataUserModel.email)
                .set(map)
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
        }
    }

    fun register(
        email: String,
        password: String,
        username: String,
        phone: String,
        content: () -> Unit
    ) {
        val auth = Firebase.auth
        if (email.isNotEmpty()
            && password.isNotEmpty()
            && username.isNotEmpty()
            && phone.isNotEmpty()
        ) {
            if (email.endsWith("@gmail.com") && password.length >= 8) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        // success
                        insertUserModel(
                            userModel = DataUserModel(
                                id = 0,
                                username = username,
                                email = email,
                                phone = phone,
                                password = password
                            )
                        )

                        sendUserFirestore(
                            dataUserModel = DataUserModel(
                                id = 0,
                                username = username,
                                email = email,
                                phone = phone,
                                password = password
                            )
                        )
                        content()
                    }
                    .addOnFailureListener {
                        // fail
                    }
            }
        }
    }

    fun login(email: String, password: String) {
        val auth = Firebase.auth
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // success
                    getUserByEmail(
                        email, DataUserModel(
                            id = 0,
                            username = "",
                            email = email,
                            phone = "",
                            password = ""
                        )
                    )

                }
                .addOnFailureListener {
                    // fail
                }
        }
    }

    fun getAllUsersFromFirebase() {
        val db = Firebase.firestore
        val list = mutableListOf<DataUserModel>()
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("Users").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()
                    for (doc in docs) {
                        val username = doc.get("username").toString()
                        val email = doc.get("email").toString()
                        val phone = doc.get("phone").toString()
                        val password = doc.get("password").toString()

                        val dum = DataUserModel(
                            id = 0,
                            username = username,
                            email = email,
                            phone = phone,
                            password = password
                        )

                        list.add(dum)
                    }
                    dumsFromFirebase.value = list
                }
            }
        }
    }

    fun send(email: String, navController: NavController, content: () -> Unit) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                // success
                content()
            }
            .addOnFailureListener {
                // fail
            }
    }

}