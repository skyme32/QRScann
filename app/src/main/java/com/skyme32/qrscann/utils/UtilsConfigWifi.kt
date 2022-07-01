package com.skyme32.qrscann.utils

import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.mlkit.vision.barcode.common.Barcode

@RequiresApi(Build.VERSION_CODES.R)
fun connectEncryptWifi(wifi: Barcode.WiFi): List<WifiNetworkSuggestion> {

    val suggestion1 = WifiNetworkSuggestion.Builder()
        .setSsid(wifi.ssid.toString())
        .setWapiPassphrase(wifi.password.toString())
        .build()

    val suggestion2 = WifiNetworkSuggestion.Builder()
        .setSsid(wifi.ssid.toString())
        .setWpa2Passphrase(wifi.password.toString())
        .build()

    val suggestion3 = WifiNetworkSuggestion.Builder()
        .setSsid(wifi.ssid.toString())
        .setWpa3Passphrase(wifi.password.toString())
        .build()

    return listOf(suggestion1, suggestion2, suggestion3)
}