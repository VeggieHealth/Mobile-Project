package com.example.veggiehealth.ui.game

import com.example.veggiehealth.R

object Constants {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getFiveQuestions(): ArrayList<Question> {
        // Gantilah ini dengan logika pemilihan 5 pertanyaan
        val allQuestions = getQuestions()
        return ArrayList(allQuestions.subList(0, 5))
    }

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1, "What vegetable is this?",
            R.drawable.veggie_bayam,
            "Spinach", "Tomato",
            "Carrot", "Cabbage", 1
        )

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "What vegetable is this?",
            R.drawable.veggie_brokoli,
            "Spinach", "Kangkung",
            "Broccoli", "Potato", 3
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "What vegetable is this?",
            R.drawable.veggie_kangkung,
            "Tomato", "Broccoli",
            "Carrot", "Kangkung", 4
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "What vegetable is this?",
            R.drawable.veggie_kentang,
            "Mustard", "Potato",
            "Long Beans", "Tomato", 2
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "What vegetable is this?",
            R.drawable.veggie_sawi,
            "Potato", "Carrot",
            "Mustard", "Broccoli", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "What vegetable is this?",
            R.drawable.veggie_kacangpanjang,
            "Long Beans", "Broccoli",
            "Terrong", "Kangkung", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "What vegetable is this?",
            R.drawable.veggie_terong,
            "Tomato", "Kangkung",
            "Terrong", "Spinach", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "What vegetable is this?",
            R.drawable.veggie_timun,
            "Spinach", "Carrto",
            "Potato", "Cucumber", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "What vegetable is this?",
            R.drawable.veggie_tomato,
            "Sweet Potato", "Tomato",
            "Potato", "Broccoli", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, "What vegetable is this?",
            R.drawable.veggie_wortel,
            "Carrot", "Long Beans",
            "Terrong", "Mustard", 1
        )

        questionsList.add(que10)

        return questionsList
    }

}