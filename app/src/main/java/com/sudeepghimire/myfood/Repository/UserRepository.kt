package com.sudeepghimire.myfood.Repository


import com.sudeepghimire.myfood.API.UserAPI
import com.sudeepghimire.myfood.entity.User
import com.sudeepghimire.myfood.response.LoginResponse
import com.sudeepghimire.myfood.API.MyApiRequest
import com.sudeepghimire.myfood.API.ServiceBuilder


class UserRepository :
    MyApiRequest(){
    private val UserAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //Register User

    suspend fun registerUser(user: User) : LoginResponse {
        return apiRequest {
            UserAPI.registerUser(user)
        }
    }
    //Login User
    suspend fun loginUser(email : String,password : String) : LoginResponse{
        return apiRequest {
            UserAPI.checkUser(email,password)
        }
    }
}