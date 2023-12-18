package com.example.veggiehealth.ui.game

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.veggiehealth.R
import com.example.veggiehealth.databinding.ActivityGameBinding
import com.example.veggiehealth.ui.result.ResultActivity

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGameBinding
    private var mCurrentPosition: Int = 0
    private var mQuestionsList: ArrayList<Question>? = null
    private var mCorrectAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> handleOptionClick(binding.tvOptionOne, 1)
            R.id.tv_option_two -> handleOptionClick(binding.tvOptionTwo, 2)
            R.id.tv_option_three -> handleOptionClick(binding.tvOptionThree, 3)
            R.id.tv_option_four -> handleOptionClick(binding.tvOptionFour, 4)
        }
    }

    private fun handleOptionClick(tv: TextView, selectedOptionNum: Int) {
        if (mCurrentPosition < mQuestionsList?.size ?: 0) {
            val question = mQuestionsList?.get(mCurrentPosition)
            if (question?.correctAnswer == selectedOptionNum) {
                mCorrectAnswers++
            }
            answerView(question?.correctAnswer ?: 0)

            mCurrentPosition++
            if (mCurrentPosition < mQuestionsList?.size ?: 0) {
                setQuestion()
            } else {
                showResult()
            }
        }
    }

    private fun answerView(answer: Int) {
        val selectedOptionView: TextView = when (answer) {
            1 -> binding.tvOptionOne
            2 -> binding.tvOptionTwo
            3 -> binding.tvOptionThree
            4 -> binding.tvOptionFour
            else -> return
        }

        selectedOptionView.isActivated
    }

    private fun setQuestion() {
        mQuestionsList?.shuffle()
        mQuestionsList = mQuestionsList?.take(5) as ArrayList<Question>
        val question = mQuestionsList?.get(mCurrentPosition)
        defaultOptionsView()

        binding.progressBar.progress = mCurrentPosition + 1
        binding.tvProgress.text = "${mCurrentPosition + 1}/${binding.progressBar.max}"

        binding.tvQuestion.text = question?.question
        binding.ivImage.setImageResource(question?.image ?: 0)
        binding.tvOptionOne.text = question?.optionOne
        binding.tvOptionTwo.text = question?.optionTwo
        binding.tvOptionThree.text = question?.optionThree
        binding.tvOptionFour.text = question?.optionFour
    }

    private fun defaultOptionsView() {
        val options = listOf(
            binding.tvOptionOne,
            binding.tvOptionTwo,
            binding.tvOptionThree,
            binding.tvOptionFour
        )

        options.forEach { option ->
            option.typeface = Typeface.DEFAULT

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
