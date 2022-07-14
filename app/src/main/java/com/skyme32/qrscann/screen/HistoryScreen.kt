package com.skyme32.qrscann.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skyme32.qrscann.R
import com.skyme32.qrscann.ui.component.RowMainScreen


@Composable
fun HistoryScreen(navController: NavHostController) {
    NavTopAppBarMain(navController)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavTopAppBarMain(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(
                            text = stringResource(id = R.string.history_title),
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            )
        },
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
