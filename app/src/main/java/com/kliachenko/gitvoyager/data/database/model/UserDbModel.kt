package com.kliachenko.gitvoyager.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "users",
    indices = [Index ("login", unique = true)]
)
data class UserDbModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("avatar_url")
    val avatarUrl: String,

    @ColumnInfo(
        "login",
        collate = ColumnInfo.NOCASE)
    val login: String,

    @ColumnInfo("repos_url")
    val reposUrl: String,
)
