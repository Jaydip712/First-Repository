package com.jdgohel.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    lateinit var fireDataReference: DatabaseReference
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

        val fireDatabase = Firebase.database
        val myRef = fireDatabase.getReference("contacts")
        myRef.setValue("Hello, World!")

//        fireDataReference = Firebase.database.reference



        btAdd.setOnClickListener {
//            database.insertData(ContactModel(name = etName.text.toString(), number = etNumber.text.toString()))
            writeNewUser("1" , etName.text.toString() , etNumber.text.toString())
            etNumber.setText("")
            etName.setText("")

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    Toast.makeText(this@MainActivity,"onDataChange", Toast.LENGTH_SHORT).show()

                    val value = snapshot.getValue()
                    Log.d("ohaowhd", "Value is: $value")

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, error.message
                        .toString(), Toast.LENGTH_SHORT).show()
                }
            })

        }

        btShow.setOnClickListener {
            val intent = Intent(this, ShowContacts::class.java)
            startActivity(intent)

        }

    }

    private fun writeNewUser(userId:String,userName:String , userEmail:String){
        val user = User(userName , userEmail)
        fireDataReference.child("users").child(userId).setValue(user)
    }
}