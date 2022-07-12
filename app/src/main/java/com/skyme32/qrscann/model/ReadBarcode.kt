package com.skyme32.qrscann.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.mlkit.vision.barcode.common.Barcode

@Entity(tableName = "barcode")
class ReadBarcode(barcode: Barcode, imageId: Int) {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "barcodeId")
    var id: Int = 0;

    @ColumnInfo(name = "rawValue")
    var rawValue: String = ""

    @ColumnInfo(name = "valueType")
    var valueType: Int = 0

    @ColumnInfo(name = "imageId")
    var imageId: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "url")
    var url: String = ""

    @ColumnInfo(name = "body")
    var body: String = ""

    @ColumnInfo(name = "address")
    var address: String = ""

    @ColumnInfo(name = "subject")
    var subject: String = ""

    @ColumnInfo(name = "encryptionType")
    var encryptionType: Int = 0

    @ColumnInfo(name = "ssid")
    var ssid: String = ""

    @ColumnInfo(name = "password")
    var password: String = ""

    init {
        this.rawValue = barcode.rawValue.toString()
        this.valueType = barcode.valueType
        this.imageId = imageId
        this.title = barcode.url?.title.toString()
        this.url = barcode.url?.url.toString()
    }
}