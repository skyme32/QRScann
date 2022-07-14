package com.skyme32.qrscann.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Camera: BottomBarScreen(
        route = "Camera",
        title = "Camera",
        icon = Icons.Default.Camera
    )
    object History: BottomBarScreen(
        route = "History",
        title = "History",
        icon = Icons.Default.History
    )
}