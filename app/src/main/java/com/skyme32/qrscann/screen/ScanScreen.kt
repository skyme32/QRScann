package com.skyme32.qrscann.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.skyme32.qrscann.R
import com.skyme32.qrscann.camera.MLKitScan


private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST = 34

private fun foregroundPermissionApproved(context: Context): Boolean {
    return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.CAMERA
    )
}

private fun requestForegroundPermission(context: Context) {
    val provideRationale = foregroundPermissionApproved(context)

    if (provideRationale) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.CAMERA), REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST
        )
    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.CAMERA), REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST
        )
    }
}

@Composable
fun ScanScreen(navController: NavHostController) {
    navTopAppBarScan(navController)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun navTopAppBarScan(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    /*
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back),
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.padding(16.dp))
                     */
                    Text(
                        text = stringResource(id = R.string.title_scann),
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    ) {
        requestForegroundPermission(LocalContext.current)
        MLKitScan()
    }
}