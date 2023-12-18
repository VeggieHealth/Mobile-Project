package com.example.veggiehealth.ui.home

import android.content.Intent
import android.graphics.Camera
import android.content.Intent
import android.os.Bundle
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.veggiehealth.R
import com.example.veggiehealth.databinding.FragmentHomeBinding
import com.example.veggiehealth.ui.camera.CameraActivity
import com.example.veggiehealth.ui.list.VeggieListActivity
import com.example.veggiehealth.ui.game.GameActivity
import com.google.android.material.card.MaterialCardView

import com.example.veggiehealth.ui.list.VeggieListActivity


class HomeFragment : Fragment(), View.OnClickListener {

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

        // Menggunakan aktivitas yang mengandung fragment
        val parentActivity = activity

        binding.cvScan.setOnClickListener {
            val intent = Intent(parentActivity, CameraActivity::class.java)
            startActivity(intent)
        }
        binding.btnListHome.setOnClickListener {
            // Membuat intent dan memulai aktivitas dari aktivitas yang mengandung fragment
            val intent = Intent(parentActivity, VeggieListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardViewQuiz: MaterialCardView = view.findViewById(R.id.cv_quiz)
        cardViewQuiz.setOnClickListener {
            // Start GameActivity when cv_quiz button is clicked
            val intent = Intent(activity, GameActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.cv_scan -> {
                // Handle click for cv_scan button
                // Add the logic for what you want to do when cv_scan is clicked
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}