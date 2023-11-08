package com.example.cameraappexample

import android.app.Activity
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val CAMERA_PERMISSION_REQUEST_CODE = 10
    private lateinit var capturedImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        capturedImage = findViewById(R.id.camera)

        val hasCameraPermission =
            checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        if (!hasCameraPermission) {
            val permissions = arrayOf(Manifest.permission.CAMERA)
            requestPermissions(permissions, CAMERA_PERMISSION_REQUEST_CODE)
        }
        else {
            captureAndDisplayImage()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                    "Display camera content",
                    Toast.LENGTH_SHORT
                ).show()
                captureAndDisplayImage()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun captureAndDisplayImage() {
        Toast.makeText(this,
            "Display camera image",
            Toast.LENGTH_SHORT
        ).show()

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra("no_interaction", true)
        startActivityForResult(cameraIntent, CAMERA_PERMISSION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val capturedImageBitmap = data!!.extras!!["data"] as Bitmap
            capturedImage.setImageBitmap(capturedImageBitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}