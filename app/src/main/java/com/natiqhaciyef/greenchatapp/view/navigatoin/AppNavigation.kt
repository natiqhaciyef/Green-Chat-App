package com.natiqhaciyef.greenchatapp.view.navigatoin

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.greenchatapp.view.screens.home.HomeScreen
import com.natiqhaciyef.greenchatapp.view.screens.registration.LoginScreen
import com.natiqhaciyef.greenchatapp.view.screens.registration.RegisterScreen
import com.natiqhaciyef.greenchatapp.view.screens.registration.ResetPasswordScreen
import com.natiqhaciyef.greenchatapp.view.screens.registration.SplashScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenID.Splash.name){
        composable(route = ScreenID.Splash.name){
            SplashScreen(navController)
        }

        composable(route = ScreenID.Registration.name){
            RegisterScreen(navController)
        }

        composable(route = ScreenID.Login.name){
            LoginScreen(navController)
        }

        composable(route = ScreenID.ResetPassword.name){
            ResetPasswordScreen(navController = navController)
        }

        composable(route = ScreenID.Home.name){
            HomeScreen(navController = navController)
        }
    }
}