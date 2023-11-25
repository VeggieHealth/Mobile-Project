package com.example.veggiehealth.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veggiehealth.databinding.ActivityRegisterBinding
import com.example.veggiehealth.ui.login.LoginActivity


class RegisterActivity : AppCompatActivity() {
    private lateinit var registerBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.tvSignInRegister.setOnClickListener {
            val signInIntent = Intent(this, LoginActivity::class.java)
            startActivity(signInIntent)
        }
    }
}