package com.kliachenko.gitvoyager.data.repository

import com.kliachenko.gitvoyager.data.network.ApiService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun main() = runBlocking {

    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)


    var since = 0
    val response1 = api.getUsers(since)
    since+=30
    val response2 = api.getUsers(since)
    since+=30
    val response3 = api.getUsers(since)

    println("Response1: ${response1.body()}")
    println("Response2: ${response2.body()}")
    println("Response3: ${response3.body()}")

//    val header = response.headers()["Link"]

//    println("Users: ${response.body()}")
//    println("Header: ${header}")
}