package com.example.veggiehealth.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.veggiehealth.ViewModelFactory
import com.example.veggiehealth.databinding.ActivityRegisterBinding
import com.example.veggiehealth.di.ResultState
import com.example.veggiehealth.ui.login.LoginActivity


class RegisterActivity : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var registerBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerAction()
        supportActionBar?.hide()
    }
    private fun registerAction() {
        registerBinding.buttonRegister.setOnClickListener {
            val username = registerBinding.usernameEditText.text.toString()
            val email = registerBinding.emailEditTextRegister.text.toString()
            val password = registerBinding.passwordEditTextRegister.text.toString()

            viewModel.register(username, email, password).observe(this) { response ->
                if (response!=null) {
                    Log.d("Response",response.toString())
                    when(response) {
                        is ResultState.Loading -> {
                            showLoading(true)
                        }
                        is ResultState.Success -> {
                            showLoading(false)
                            AlertDialog.Builder(this).apply {
                                setTitle("Register Status")
                                setMessage(response.data.message)
                                setPositiveButton("Lanjut") {dialog,_ -> dialog.dismiss()
                                    finish()
                                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)

                                }
                                create()
                                show()
                            }
                        }
                        is ResultState.Error -> {
                            showLoading(false)
                            AlertDialog.Builder(this).apply {
                                setTitle("Register Failed")
                                setMessage(response.error)
                                setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                                create()
                                show()
                            }
                        }
                    }
                }
            }
        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            registerBinding.progressBar.visibility = View.VISIBLE
        } else {
            registerBinding.progressBar.visibility = View.GONE
        }
    }
}

