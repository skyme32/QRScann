package com.skyme32.qrscann.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skyme32.qrscann.R
import com.skyme32.qrscann.navigation.AppScreens
import com.skyme32.qrscann.ui.component.RowMainScreen


@Composable
fun MainScreen(navController: NavHostController) {
    navTopAppBarMain(navController)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun navTopAppBarMain(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                } }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.ScanScreen.route) },
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Icon(Icons.Filled.CropFree, "")
            }
        }
    ) {
        ListMainScreen()
    }
}

@Composable
fun ListMainScreen() {
    Column(//modifier = Modifier.fillMaxSize()
    ) {
        RowMainScreen(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
        RowMainScreen(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
        RowMainScreen(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
    }
}
