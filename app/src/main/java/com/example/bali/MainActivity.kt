package com.example.bali

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bali.homePage.HomePageFragment
import com.example.bali.utils.Utils.initializeDatabaseWithPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*
        // initialize the database with a new place
        lifecycleScope.launch(Dispatchers.IO) {
            initializeDatabaseWithPlace(this@MainActivity.application)
        }*/
        /*

        // Now setup the HomePageFragment or any other initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomePageFragment())
                .commit()
        }*/
    }
}