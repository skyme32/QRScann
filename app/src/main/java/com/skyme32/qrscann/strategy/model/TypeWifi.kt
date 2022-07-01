package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition
import com.skyme32.qrscann.utils.connectEncryptWifi

object TypeWifi: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_WIFI
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun getIntent(context: Context, barcode: Barcode): Intent {

        val suggestionsList = connectEncryptWifi(barcode.wifi!!)
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val status = wifiManager.addNetworkSuggestions(suggestionsList)
        if (status != WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
            Log.i("wifi",barcode.wifi?.encryptionType.toString())
        }

        return Intent(context, null)
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_wifi)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_wifi
    }

    override fun isIntent(): Boolean {
        return true
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_wifi)
    }
}