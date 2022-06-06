package com.skyme32.qrscann.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skyme32.qrscann.R
import com.skyme32.qrscann.ui.RowImage


@Composable
fun MainScreen(navController: NavHostController) {
    navTopAppBarMain(navController)
}

@Composable
fun navTopAppBarMain(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                } }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colors.primaryVariant
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        ListMainScreen()
    }
}

@Composable
fun ListMainScreen() {
    Column(modifier = Modifier.fillMaxSize()
    ) {
        RowListMainScreen()
    }
}

@Composable
fun RowListMainScreen() {
    Row(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(6.dp)) {
        RowImage(resource = R.drawable.ic_launcher_foreground, description = "MyImage")
    }
}

@Preview(showSystemUi = true)
@Composable
fun defaultMainScreen() {
    ListMainScreen()
}


