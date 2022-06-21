package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition

class TypeUrl: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_URL
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(barcode.url?.url.toString()))
    }

    override fun getText(): String {
        return Resources.getSystem().getString(R.string.is_url)
    }

    override fun getImatge(): Int {
        return R.drawable.ic_baseline_language
    }


}