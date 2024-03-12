package com.example.bali.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bali.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SignUpFragment())
            .commit()
    }

}