package com.example.veggiehealth.data

import com.example.veggiehealth.data.pref.UserModel
import com.example.veggiehealth.data.pref.UserPreference
import com.example.veggiehealth.data.remote.response.LoginResponse
import com.example.veggiehealth.data.remote.response.RegisterResponse
import com.example.veggiehealth.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
){
    suspend fun register(username: String, email: String, password: String): RegisterResponse {
        return apiService.register(username, email, password)
    }

    suspend fun saveSession(user: UserModel){
        userPreference.saveSession(user)
    }

    suspend fun login(email: String,password: String): LoginResponse {
        return apiService.login(email, password)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    companion object {
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository = UserRepository(userPreference, apiService)
    }
}