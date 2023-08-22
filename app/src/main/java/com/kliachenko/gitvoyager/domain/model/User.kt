package com.kliachenko.gitvoyager.domain.model

data class User(
    val id: Int,
    val avatarUrl: String,
    val login: String,
    val reposUrl: String,
)
