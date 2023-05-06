package com.natiqhaciyef.greenchatapp.view.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.greenchatapp.R
import com.natiqhaciyef.greenchatapp.data.model.DataUserModel
import com.natiqhaciyef.greenchatapp.domain.util.obj.FontList
import com.natiqhaciyef.greenchatapp.ui.theme.AppDarkGray
import com.natiqhaciyef.greenchatapp.ui.theme.AppGreen
import com.natiqhaciyef.greenchatapp.ui.theme.Brown
import com.natiqhaciyef.greenchatapp.view.components.BottomShadow
import com.natiqhaciyef.greenchatapp.view.components.SocialMediaRow
import com.natiqhaciyef.greenchatapp.view.navigatoin.ScreenID
import com.natiqhaciyef.greenchatapp.view.viewmodel.registration.RegistrationViewModel


@Preview
@Composable
fun LoginScreen(navController: NavController = rememberNavController()) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LoginMainPart2(navController)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Preview
@Composable
private fun LoginMainPart2(navController: NavController = rememberNavController()) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val check = remember { mutableStateOf(false) }
    val viewModel: RegistrationViewModel = hiltViewModel()
    val dumsFromFirebase = remember { viewModel.dumsFromFirebase }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(AppGreen),
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Login",
                fontSize = 35.sp,
                color = Color.White,
                fontFamily = FontList.fontFamilyLobster,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                keyboardOptions = KeyboardOptions().copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
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
                ),
            )

            BottomShadow(padding = 23.dp)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = password.value,
                onValueChange = {
                    password.value = it
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Password", color = AppDarkGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = AppDarkGray
                    )
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedBorderColor = AppGreen,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppGreen
                ),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle.Default.copy(
                    fontSize = 17.sp, fontWeight = FontWeight.SemiBold
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisibility) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisibility) "Hide password" else "Show password"

                    IconButton(modifier = Modifier.padding(end = 15.dp),
                        onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(imageVector = image, description)
                    }
                })

            BottomShadow(padding = 23.dp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Forgot password ?",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .clickable {
                        navController.navigate(ScreenID.ResetPassword.name)
                    },
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(35.dp))
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier
                .height(55.dp)
                .width(200.dp),
            onClick = {
                login(
                    email = email,
                    password = password,
                    viewModel = viewModel,
                    dumsFromFirebase = dumsFromFirebase,
                    check = check,
                    navController = navController
                )
            },
            shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Brown
            )
        ) {
            Text(
                text = "Login",
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
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(60.dp)
                    .background(AppDarkGray),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = "Or continue with social media",
                color = AppGreen,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(60.dp)
                    .background(AppDarkGray),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SocialMediaRow(
                text = "Google",
                image = R.drawable.googleicon,
                color = AppGreen,
                textColor = Color.White
            ) {
                // onClick
            }
            Spacer(modifier = Modifier.width(20.dp))
            SocialMediaRow(
                text = "Facebook",
                image = R.drawable.facebookicon,
                color = AppGreen,
                textColor = Color.White
            ) {
                // onClick
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "You don't have account ?",
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier.clickable {
                    navController.navigate(ScreenID.Registration.name)
                },
                text = "Sign up",
                color = Red,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}


private fun login(
    email: MutableState<String>,
    password: MutableState<String>,
    dumsFromFirebase: MutableState<List<DataUserModel>>,
    check: MutableState<Boolean>,
    viewModel: RegistrationViewModel,
    navController: NavController
) {
    val test = dumsFromFirebase.value.filter {
        email.value == it.email &&
                password.value == it.password
    }

    check.value = test.isNotEmpty()

    if (check.value) {
        viewModel.login(email.value, password.value)
//        navController.navigate(ScreenID.TestScreen.name)
        navController.navigate(ScreenID.Home.name)
    }
}

