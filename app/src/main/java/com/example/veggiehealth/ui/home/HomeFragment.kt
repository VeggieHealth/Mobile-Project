package com.example.veggiehealth.ui.home

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.veggiehealth.R
import com.example.veggiehealth.databinding.FragmentHomeBinding
import com.example.veggiehealth.ui.camera.CameraActivity
import com.example.veggiehealth.ui.list.VeggieListActivity
import com.example.veggiehealth.ui.game.GameActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTimeOfDayGreeting()
        val parentActivity = activity

        binding.cvScan.setOnClickListener {
            val intent = Intent(parentActivity, CameraActivity::class.java)
            startActivity(intent)
        }
        binding.btnListHome.setOnClickListener {
            val intent = Intent(parentActivity, VeggieListActivity::class.java)
            startActivity(intent)
        }
        binding.cvQuiz.setOnClickListener {
            val intent = Intent(parentActivity, GameActivity::class.java)
            startActivity(intent)
        }

    }
    private fun updateTimeOfDayGreeting() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val greetingText = when (hour) {
            in 6..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            in 18..23 -> "Good Evening"
            in 23..23 -> "Good Evening"
            in 0..5 -> "Good Night"
            else -> "Hello"
        }


        binding.tvWelcomeHome.text = greetingText
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}