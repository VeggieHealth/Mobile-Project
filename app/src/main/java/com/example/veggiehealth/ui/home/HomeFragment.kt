package com.example.veggiehealth.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}