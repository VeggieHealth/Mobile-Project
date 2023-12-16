package com.example.veggiehealth.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.veggiehealth.data.pref.UserModel
import com.example.veggiehealth.data.pref.UserPreference
import com.example.veggiehealth.data.remote.response.DetailVegetableResponse
import com.example.veggiehealth.data.remote.response.LoginResponse
import com.example.veggiehealth.data.remote.response.RegisterResponse
import com.example.veggiehealth.data.remote.response.VegetablesItem
import com.example.veggiehealth.data.remote.retrofit.ApiService
import com.example.veggiehealth.di.ResultState
import com.example.veggiehealth.ui.list.VegetablesPagingSource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

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
    fun getlistSayuran(): LiveData<PagingData<VegetablesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                VegetablesPagingSource(apiService)
            }
        ).liveData
    }
    fun getDetailSayuran(id: String) = liveData {
        emit(ResultState.Loading)
        try {
            val detailResponse = apiService.getDetailVegetables(id)
            emit(ResultState.Success(detailResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DetailVegetableResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    companion object {
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository = UserRepository(userPreference, apiService)
    }
}