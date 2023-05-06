package com.natiqhaciyef.greenchatapp.view.screens.registration

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.greenchatapp.R
import com.natiqhaciyef.greenchatapp.view.viewmodel.registration.RegistrationViewModel
import com.natiqhaciyef.greenchatapp.domain.util.obj.FontList
import com.natiqhaciyef.greenchatapp.view.navigatoin.ScreenID
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
//@Preview
@Composable
fun SplashScreen(
    navController: NavController
) {
    SplashScreen2(navController)
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen2(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel: RegistrationViewModel = hiltViewModel()
    val dumsFromFirebase = remember { viewModel.dumsFromFirebase }
    val checkResult = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        coroutineScope.launch {
            delay(2000)
            val auth = Firebase.auth.currentUser
            val test = dumsFromFirebase.value.filter {
                if (auth != null)
                    auth.email == it.email
                else
                    false
            }

            println(test)
            checkResult.value = test.isNotEmpty()


            if (checkResult.value) {
                navController.navigate(ScreenID.Home.name)
            } else
                navController.navigate(ScreenID.Login.name)
        }



        Image(
            painter = painterResource(id = R.drawable.splash_screen_icon),
            contentDescription = "App new icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .size(70.dp)
        )

        Text(
            modifier = Modifier,
            text = "Green Chat",
            fontFamily = FontList.fontFamilyLobster,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(50.dp))
    }
}



