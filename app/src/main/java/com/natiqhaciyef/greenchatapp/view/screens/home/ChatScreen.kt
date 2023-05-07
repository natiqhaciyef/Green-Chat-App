package com.natiqhaciyef.greenchatapp.view.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.greenchatapp.data.model.ChatModel
import com.natiqhaciyef.greenchatapp.ui.theme.AppGreen
import com.natiqhaciyef.greenchatapp.view.navigatoin.NavData
import com.natiqhaciyef.greenchatapp.view.viewmodel.home.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
fun ChatScreen() {
    val coroutineScope = rememberCoroutineScope()
    val viewModel: HomeViewModel = hiltViewModel()
    val user = viewModel.getUserByEmail(NavData.email)


    val userEmail = user.email
    val userName = user.username
    var data = remember{ mutableStateOf<List<ChatModel>>(mutableListOf()) }
//    var data = viewModel.chartFlow.collectAsState(initial = mutableListOf())
    data.value.filter {
        it.users.contains(userEmail)
    }

    coroutineScope.launch {
        viewModel.chartFlow.collectLatest { value ->
            data.value = value
        }
    }


    val message = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGreen),
        verticalArrangement = Arrangement.Bottom,

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .size(35.dp),
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Arrow back",
                tint = Color.White
            )
            Icon(
                modifier = Modifier
                    .size(70.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                tint = Color.White
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, bottom = 5.dp)
                        .fillMaxWidth(0.70f),
                    text = userName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )

                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, bottom = 5.dp)
                        .fillMaxWidth(0.70f),
                    text = "Online",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }

            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(35.dp),
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings",
                tint = Color.White
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .background(Color.White),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (data.value.isNotEmpty()) {
                LazyColumn{
                    items(data.value[0].chat) { messageChat ->
                        MessageComponent(message = messageChat)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                value = message.value,
                onValueChange = {
                    message.value = it
                },
                readOnly = false,
                enabled = true,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                placeholder = {
                    Text(
                        text = "Write message...",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {

                            },
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                    )
                }
            )
        }
    }
}

val email = Firebase.auth.currentUser?.email!!

@Composable
private fun MessageComponent(message: String) {
    if (message.startsWith(email)) {
        val mess = message.removePrefix(email).removePrefix(" : ")
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = mess,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }
    } else {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                modifier = Modifier,
                text = message,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppGreen,
                textAlign = TextAlign.Start
            )
        }

    }
}