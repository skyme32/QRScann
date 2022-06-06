package com.skyme32.qrscann.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skyme32.qrscann.screen.MainScreen
import com.skyme32.qrscann.screen.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
    }
}