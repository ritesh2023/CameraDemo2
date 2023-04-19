package com.example.carmerademo2

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var imageView : ImageView
    private lateinit var btnCamera : Button
    private val camLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result ->
            if(result.resultCode == RESULT_OK && result.data != null){
                val takenImage = result.data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(takenImage)

            }
        })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        btnCamera = findViewById(R.id.btnCamera)

        btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(this .packageManager) != null)
            {
                //launch
                camLauncher.launch(intent)
            }else{
                Toast.makeText(this," Camera is Not Installed...",Toast.LENGTH_SHORT).show()

            }
        }
    }
}