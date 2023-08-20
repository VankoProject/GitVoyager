package com.kliachenko.gitvoyager.data.network

import com.kliachenko.gitvoyager.data.network.model.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET("users")
   suspend fun getUsers(
      @Query("since") since: Int,
   ): Response<List<UserDto>>


}