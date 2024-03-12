package com.example.bali

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bali.login.LoginActivity
import com.example.bali.signup.SignUpActivity


class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

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
