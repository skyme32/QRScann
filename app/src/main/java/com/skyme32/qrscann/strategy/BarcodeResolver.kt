package com.skyme32.qrscann.strategy

import android.os.Build
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.strategy.model.*
import java.util.function.Function
import java.util.stream.Collectors
import javax.inject.Inject

class BarcodeResolver {

    @Inject
    private var barcodeType: List<BarcodeDefinition> = listOf(
        TypeContactInfo,
        TypeUrl,
        TypeUnknown,
        TypeText,
        TypeWifi
    )

    private var mapBarcodeType: Map<Int, BarcodeDefinition> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        barcodeType.stream().collect(Collectors.toMap(BarcodeDefinition::appliedTypeBarcode, Function.identity()))
    } else {
        HashMap()
    }

    fun getBy(barcodeType: Int?): BarcodeDefinition? {
        var barcodeTypeFinish = Barcode.TYPE_UNKNOWN;

        if (barcodeType != null && mapBarcodeType[barcodeType] != null) {
            barcodeTypeFinish = barcodeType
        }

        return mapBarcodeType[barcodeTypeFinish]
    }
}