package com.kliachenko.gitvoyager.data.network

import com.kliachenko.gitvoyager.data.network.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   @GET("users")
   suspend fun getUsers(
      @Query("since") since: Int,
      @Query("per_page") pageSize: Int
   ): List<UserDto>

}