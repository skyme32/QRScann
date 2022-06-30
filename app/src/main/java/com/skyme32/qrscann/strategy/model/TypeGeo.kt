package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition

object TypeGeo: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_GEO
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {

        return Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:0,0?q=${barcode.geoPoint?.lat},${barcode.geoPoint?.lng}")
            setPackage("com.google.android.apps.maps")
        }
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_geo)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_location
    }

    override fun isIntent(): Boolean {
        return true
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_geo)
    }
}