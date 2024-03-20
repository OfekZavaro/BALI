package com.example.bali

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bali.homePage.HomePageFragment
import com.example.bali.login.LoginActivity
import com.example.bali.signup.SignUpActivity
import com.example.bali.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // initialize the database with a new place
        Utils.initializeDatabaseWithPlace(application)

        val registerButton: Button = findViewById(R.id.create_account_button)
        val loginButton: Button = findViewById(R.id.login_button)

        registerButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish() // Finish the Welcome activity to make it disappear
        }

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Finish the Welcome activity to make it disappear
        }


    }
}
