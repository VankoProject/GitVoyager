package com.kliachenko.gitvoyager.data.network

import com.kliachenko.gitvoyager.data.network.model.UsersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

   @GET("users")
   suspend fun getUsers(
       @Header("Accept") acceptHeader: String
   ): Response<UsersDto>

}