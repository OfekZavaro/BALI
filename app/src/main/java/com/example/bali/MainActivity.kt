package com.example.bali

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bali.database.DatabaseInstance
import com.example.bali.database.entities.User
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //FirebaseFirestore firestore;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //val id = "uniqueUserId"
        //val database = Firebase.database
        //val userRef = database.getReference("users").child(id)
        //userRef.setValue(User(name = "John", email = "john@example.com", profilePhoto = "url-to-photo"))

        //val db = DatabaseInstance.getDatabase(applicationContext)
        //val userDao = db.userDao()
        //userDao.insertUser(User(name = "John", email = "john@example.com", profilePhoto = "url-to-photo"))
    }
}