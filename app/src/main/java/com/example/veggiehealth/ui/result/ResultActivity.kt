package com.example.veggiehealth.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.veggiehealth.ui.game.Constants
import com.example.veggiehealth.MainActivity
import com.example.veggiehealth.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        binding.tvScore.text = "Your Score is $correctAnswers out of $totalQuestions."

        binding.buttonFinish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
            finish()
        }
    }
}