package com.github.weslleystos.emonitoria.ui.navigation

sealed class Screen(val Route: String) {
    object Splash : Screen(Route = "splash_screen")
    object SignIn : Screen(Route = "sign_in_screen")
    object SignUp : Screen(Route = "sign_up_screen")
}
