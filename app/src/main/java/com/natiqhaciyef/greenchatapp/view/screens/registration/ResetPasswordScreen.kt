package com.natiqhaciyef.greenchatapp.view.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.greenchatapp.R
import com.natiqhaciyef.greenchatapp.view.components.BottomShadow
import com.natiqhaciyef.greenchatapp.view.viewmodel.registration.RegistrationViewModel
import com.natiqhaciyef.greenchatapp.domain.util.obj.FontList
import com.natiqhaciyef.greenchatapp.ui.theme.AppDarkGray
import com.natiqhaciyef.greenchatapp.ui.theme.AppGreen
import com.natiqhaciyef.greenchatapp.ui.theme.Brown
import com.natiqhaciyef.greenchatapp.view.navigatoin.ScreenID

//@Preview
@Composable
fun ResetPasswordScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        it.calculateTopPadding()
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResetPasswordMainPart(navController)
        }
    }
}


@Composable
private fun ResetPasswordMainPart(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val viewModel: RegistrationViewModel = hiltViewModel()

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.6f),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        backgroundColor = AppGreen
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Reset password",
                fontSize = 35.sp,
                color = Color.White,
                fontFamily = FontList.fontFamilyLobster,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email).copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Email", color = AppDarkGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = AppDarkGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedBorderColor = AppGreen,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppGreen,
                ),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle.Default.copy(
                    fontSize = 17.sp, fontWeight = FontWeight.SemiBold
                )
            )

            BottomShadow(padding = 23.dp)

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                text = stringResource(id = R.string.reset_info),
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )


            Spacer(modifier = Modifier.height(45.dp))


            Button(
                modifier = Modifier
                    .height(55.dp)
                    .width(200.dp),
                onClick = {
                    // send reset link
                    viewModel.send(email.value, navController = navController) {
                        navController.navigate(ScreenID.Login.name)
                    }
                },
                shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Brown
                )
            ) {
                Text(
                    text = "Send",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            BottomShadow(
                modifier = Modifier
                    .width(165.dp)
                    .height(8.dp)
                    .padding(horizontal = 0.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Transparent,
                            )
                        )
                    )
            )
        }
    }
}


