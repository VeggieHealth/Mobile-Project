package com.example.veggiehealth.ui.home

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.veggiehealth.MainActivity
import com.example.veggiehealth.R
import com.example.veggiehealth.databinding.FragmentHomeBinding
import com.example.veggiehealth.ui.list.VeggieListActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan aktivitas yang mengandung fragment
        val parentActivity = activity


        binding.btnListHome.setOnClickListener {
            // Membuat intent dan memulai aktivitas dari aktivitas yang mengandung fragment
            val intent = Intent(parentActivity, VeggieListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}