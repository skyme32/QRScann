package com.skyme32.qrscann.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skyme32.qrscann.screen.DetailScreen
import com.skyme32.qrscann.screen.MainScreen
import com.skyme32.qrscann.screen.ScanScreen
import com.skyme32.qrscann.screen.SplashScreen

@ExperimentalMaterialApi
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
        composable(route = AppScreens.ScanScreen.route) {
            ScanScreen(navController)
        }
        composable(route = AppScreens.DetailScreen.route) {
            DetailScreen(navController)
        }
    }
}