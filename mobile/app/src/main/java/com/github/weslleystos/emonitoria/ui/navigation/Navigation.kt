package com.github.weslleystos.emonitoria.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.weslleystos.emonitoria.splash.ui.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.Route) {
        composable(Screen.Splash.Route) {
            SplashScreen(navController)
        }

        composable(Screen.SignIn.Route) {

        }
        composable(Screen.SignUp.Route) {

        }
    }
}

