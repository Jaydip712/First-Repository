package com.jdgohel.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = true
//d2d7321ab11942a5a61141f0a71ef02f
        val btAdd: Button = findViewById(R.id.btAdd)
        val btShow: Button = findViewById(R.id.btShow)

        val etName:EditText = findViewById(R.id.etName)
        val etNumber:EditText = findViewById(R.id.etNumber)

        val database = SqlDataBase(this)

        btAdd.setOnClickListener {
            database.insertData(ContactModel(name = etName.text.toString(), number = etNumber.text.toString()))
            etNumber.setText("")
            etName.setText("")
        }

        btShow.setOnClickListener {
            val intent = Intent(this, ShowContacts::class.java)
            startActivity(intent)

        }

    }
}