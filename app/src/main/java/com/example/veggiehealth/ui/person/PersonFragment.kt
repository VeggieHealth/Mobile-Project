package com.example.veggiehealth.ui.person

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.veggiehealth.MainActivity
import com.example.veggiehealth.R
import com.example.veggiehealth.ViewModelFactory
import com.example.veggiehealth.data.remote.response.UserProfile
import com.example.veggiehealth.databinding.FragmentPersonBinding
import com.example.veggiehealth.di.ResultState
import com.example.veggiehealth.ui.login.LoginActivity
import kotlinx.coroutines.launch


class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPersonBinding.inflate(inflater, container, false)

        binding.btnLogoutProfile.setOnClickListener {
            lifecycleScope.launch {
                try {
                    userLogout()
                    val logoutIntent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(logoutIntent)
                } catch (e: Exception) {
                    showToast("Logout failed: ${e.message}")
                }
            }
        }
        viewModel.getDetailProfile().observe(viewLifecycleOwner) { profileResult->
            when(profileResult) {
                is ResultState.Loading -> {
                    showLoading(true)
                }
                is ResultState.Success -> {
                    showLoading(false)
                    val userData = profileResult.data.userProfile
                    getDetailuser(userData)
                }
                is ResultState.Error -> {
                    showLoading(false)
                    showToast(profileResult.error)
                }
            }
        }
        return binding.root
    }
    private suspend fun userLogout() {
        viewModel.logOut()
    }
    private fun getDetailuser(user: UserProfile) {
        binding.tvUsername.text = user.userProfile
        binding.tvEmailProfile.text = user.emailProfile
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}