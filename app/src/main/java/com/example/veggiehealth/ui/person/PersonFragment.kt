package com.example.veggiehealth.ui.person

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.veggiehealth.R
import com.example.veggiehealth.databinding.FragmentPersonBinding


class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val notificationsViewModel =
            ViewModelProvider(this).get(PersonViewModel::class.java)

        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPerson
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }


}