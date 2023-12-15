package com.example.veggiehealth.ui.game

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.veggiehealth.R
import com.example.veggiehealth.databinding.ActivityGameBinding
import com.example.veggiehealth.ui.result.ResultActivity

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityGameBinding
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // START


        mQuestionsList = Constants.getQuestions()

        setQuestion()


        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.buttonSubmit.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> selectedOptionView(binding.tvOptionOne, 1)
            R.id.tv_option_two -> selectedOptionView(binding.tvOptionTwo, 2)
            R.id.tv_option_three -> selectedOptionView(binding.tvOptionThree, 3)
            R.id.tv_option_four -> selectedOptionView(binding.tvOptionFour, 4)
            R.id.button_submit -> {
                handleSubmission()
            }
        }
    }

    private fun handleSubmission() {
        if (mSelectedOptionPosition == 0) {
            mCurrentPosition++
            when {
                mCurrentPosition <= 5 -> {
                    setQuestion()
                }
                else -> {
                    showResult()
                }
            }
        } else {
            val question = mQuestionsList?.get(mCurrentPosition - 1)
            if (question?.correctAnswer == mSelectedOptionPosition) {
                mCorrectAnswers++
            }
            answerView(question?.correctAnswer ?: 0, R.drawable.correct_option_border_bg)
            if (mCurrentPosition == mQuestionsList!!.size) {
                binding.buttonSubmit.text = "FINISH"
            } else {
                binding.buttonSubmit.text = "GO TO NEXT QUESTION"
            }
            mSelectedOptionPosition = 0
        }
    }


    private fun setQuestion() {

        mQuestionsList?.shuffle()

        val question = mQuestionsList?.get(mCurrentPosition - 1)
        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.buttonSubmit.text = "FINISH"
        } else {
            binding.buttonSubmit.text = "SUBMIT"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition/${binding.progressBar.max}"

        binding.tvQuestion.text = question?.question
        binding.ivImage.setImageResource(question?.image ?: 0)
        binding.tvOptionOne.text = question?.optionOne
        binding.tvOptionTwo.text = question?.optionTwo
        binding.tvOptionThree.text = question?.optionThree
        binding.tvOptionFour.text = question?.optionFour
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this@GameActivity, R.drawable.selected_option_border_bg)
    }

    private fun defaultOptionsView() {
        val options = listOf(
            binding.tvOptionOne,
            binding.tvOptionTwo,
            binding.tvOptionThree,
            binding.tvOptionFour
        )

        options.forEach { option ->
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =
                ContextCompat.getDrawable(this@GameActivity, R.drawable.default_option_border_bg)
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> binding.tvOptionOne.background =
                ContextCompat.getDrawable(this@GameActivity, drawableView)
            2 -> binding.tvOptionTwo.background =
                ContextCompat.getDrawable(this@GameActivity, drawableView)
            3 -> binding.tvOptionThree.background =
                ContextCompat.getDrawable(this@GameActivity, drawableView)
            4 -> binding.tvOptionFour.background =
                ContextCompat.getDrawable(this@GameActivity, drawableView)
        }
    }

    private fun showResult() {
        val intent = Intent(this@GameActivity, ResultActivity::class.java)
        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size ?: 0)
        startActivity(intent)
        finish()
    }
}