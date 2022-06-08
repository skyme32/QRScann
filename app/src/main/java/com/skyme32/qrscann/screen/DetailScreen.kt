package com.skyme32.qrscann.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skyme32.qrscann.R


@Composable
fun DetailScreen(navController: NavHostController) {
    navTopAppBarDetail(navController)
}

@Composable
fun navTopAppBarDetail(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back),
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.title_detail),
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    ) {

    }
}