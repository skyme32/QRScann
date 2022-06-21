package com.skyme32.qrscann.strategy

import android.content.Context
import android.content.Intent
import com.google.mlkit.vision.barcode.common.Barcode

interface BarcodeDefinition {

    fun appliedTypeBarcode(): Int

    fun getIntent(context: Context, barcode: Barcode): Intent

    fun getText(): String

    fun getImatge(): Int
}