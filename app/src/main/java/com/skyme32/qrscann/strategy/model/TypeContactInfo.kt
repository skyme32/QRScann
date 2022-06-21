package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.navigation.AppScreens
import com.skyme32.qrscann.strategy.BarcodeDefinition


class TypeContactInfo: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_CONTACT_INFO
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {
        return Intent(context, AppScreens.MainScreen::class.java)
    }

    override fun getText(): String {
        return Resources.getSystem().getString(R.string.is_contact)
    }

    override fun getImatge(): Int {
        return R.drawable.ic_baseline_account
    }


}