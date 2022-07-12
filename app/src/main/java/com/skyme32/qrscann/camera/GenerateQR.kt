package com.skyme32.qrscann.camera

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter


fun getBitmap(qrCodeContent: String, primary: Color, secondary: Color): Bitmap {

    val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 }
    val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, 512, 512, hints)
    val size = 512 //pixels
    return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
        for (x in 0 until size) {
            for (y in 0 until size) {
                it.setPixel(x, y, if (bits[x, y]) primary.toArgb() else secondary.toArgb())
            }
        }
    }
}
