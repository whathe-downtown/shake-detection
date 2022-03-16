package com.example.shake_detection

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

class CreateActivity : AppCompatActivity() {

    private val iv: ImageView? = null
    private val text: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_qr)

        val iv = findViewById<ImageView>(R.id.qrcode);
        val text = "https://stickode.tistory.com"

        val multiFormatWriter = MultiFormatWriter()

        try {
            val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            iv!!.setImageBitmap(bitmap)
        } catch (e: Exception) {
            Toast.makeText(this, "exception $e", Toast.LENGTH_SHORT).show()
        }
    }
}