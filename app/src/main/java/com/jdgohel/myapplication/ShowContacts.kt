package com.jdgohel.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class ShowContacts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contacts)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = true

        val listView: ListView = findViewById(R.id.listview)

        val db = SqlDataBase(this)
        
        val dataList = db.getAllData()

        db.updateData(ContactModel(8 , "Avni" , "123"))

        // Create a Array adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter
        listView.divider = ContextCompat.getDrawable(this, R.drawable.divider)
        listView.dividerHeight = 8 // Height of the divider
        listView.setPadding(0, 0, 0, 16) // Adjust bottom padding as neededdsf

        Toast.makeText(this, dataList.size.toString(), Toast.LENGTH_SHORT).show()

//        db.deleteData(2)

//        db.deleteData(3)
//        db.deleteData(5)
//
//        Toast.makeText(this, dataList.size.toString(), Toast.LENGTH_SHORT).show()
    }
}