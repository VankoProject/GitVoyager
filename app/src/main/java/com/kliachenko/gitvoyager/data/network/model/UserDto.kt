package com.kliachenko.gitvoyager.data.network.model

import com.google.gson.annotations.SerializedName

data class UserDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("login")
    val login: String,

    @SerializedName("repos_url")
    val reposUrl: String,

)