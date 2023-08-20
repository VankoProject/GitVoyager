package com.kliachenko.gitvoyager.data.mapper

import com.kliachenko.gitvoyager.data.network.model.UserDto
import com.kliachenko.gitvoyager.domain.model.User

fun UserDto.toUser()= User(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    reposUrl = reposUrl
)