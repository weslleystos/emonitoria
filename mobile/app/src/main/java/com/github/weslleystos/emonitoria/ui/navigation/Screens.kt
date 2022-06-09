package com.github.weslleystos.emonitoria.ui.navigation

sealed class Screens(val route: String) {
    object Login: Screens("login_screen")
    object Register: Screens("register_screen")
}
