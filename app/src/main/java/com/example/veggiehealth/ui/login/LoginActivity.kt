package com.example.veggiehealth.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veggiehealth.MainActivity
import com.example.veggiehealth.databinding.ActivityLoginBinding
import com.example.veggiehealth.textfield.EmailText
import com.example.veggiehealth.textfield.PasswordText
import com.example.veggiehealth.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var emailText: EmailText
    private lateinit var passwordText: PasswordText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignUp.setOnClickListener {
            val signUpIntent  = Intent(this, RegisterActivity::class.java)
            startActivity(signUpIntent)

        }
        binding.button.setOnClickListener {
            val buttonIntent = Intent(this, MainActivity::class.java)
            startActivity(buttonIntent)
        }

        supportActionBar?.hide()
    }


}