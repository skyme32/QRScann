package com.skyme32.qrscann.navigation

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens("main_screen")
    object SplashScreen: AppScreens("splash_screen")
    object ScanScreen: AppScreens("scan_screen")
    object DetailScreen: AppScreens("detail_screen")
}