package com.kliachenko.gitvoyager.data.mapper

import com.kliachenko.gitvoyager.data.database.model.UserDbModel
import com.kliachenko.gitvoyager.data.network.model.UserDto
import com.kliachenko.gitvoyager.domain.model.User

fun UserDto.toUserDbModel() = UserDbModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    reposUrl = reposUrl
)

fun UserDbModel.toUser() = User(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    reposUrl = reposUrl
)