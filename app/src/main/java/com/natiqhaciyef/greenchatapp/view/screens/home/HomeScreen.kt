package com.natiqhaciyef.greenchatapp.view.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.greenchatapp.R
import com.natiqhaciyef.greenchatapp.domain.util.obj.UserList
import com.natiqhaciyef.greenchatapp.ui.theme.AppBlack
import com.natiqhaciyef.greenchatapp.ui.theme.AppDarkGray
import com.natiqhaciyef.greenchatapp.ui.theme.AppGreen
import com.natiqhaciyef.greenchatapp.ui.theme.AppYellow
import com.natiqhaciyef.greenchatapp.view.components.UserStoryItem
import com.natiqhaciyef.greenchatapp.view.components.UserChatView

@Preview
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = AppGreen,
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    modifier = Modifier
                        .padding(top = 30.dp, start = 20.dp)
                        .size(35.dp)
                        .align(Alignment.TopStart),
                    painter = painterResource(id = R.drawable.menu_icon),
                    contentDescription = "Menu Icon",
                    tint = Color.White
                )

                Icon(
                    modifier = Modifier
                        .padding(top = 35.dp, end = 20.dp)
                        .size(30.dp)
                        .align(Alignment.TopEnd),
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "App Icon",
                    tint = Color.White
                )

            }
        }
    ) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            HomeTopView(searchQuery)
            Spacer(modifier = Modifier.height(30.dp))
            HomeMainPart(searchQuery)
        }
    }
}


@Composable
private fun HomeTopView(searchQuery: MutableState<String>) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
            .padding(horizontal = 25.dp),
        value = searchQuery.value,
        onValueChange = {
            searchQuery.value = it
        },
        singleLine = true,
        enabled = true,
        readOnly = false,
        shape = RoundedCornerShape(18.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        placeholder = {
            Text(
                text = "Search",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        },
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = AppBlack,
            focusedBorderColor = AppYellow,
            backgroundColor = Color.White,
            textColor = Color.Black,
            placeholderColor = AppDarkGray
        ),
    )
}


@Composable
private fun HomeMainPart(searchQuery: MutableState<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow {
                items(UserList.storyList) { story ->
                    UserStoryItem(story)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            for (chat in UserList.chatList){
                UserChatView(userTextModel = chat)
            }
        }
    }
}