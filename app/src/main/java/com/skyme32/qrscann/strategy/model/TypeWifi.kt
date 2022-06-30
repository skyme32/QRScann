package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition

object TypeWifi: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_WIFI
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {
        return Intent(context, null)
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_wifi)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_wifi
    }

    override fun isIntent(): Boolean {
        return false
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_wifi)
    }
}