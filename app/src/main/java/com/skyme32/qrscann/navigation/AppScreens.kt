package com.skyme32.qrscann.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object MainScreen: AppScreens("main_screen")
    object DetailScreen: AppScreens("detail_screen")
}