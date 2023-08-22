package com.kliachenko.gitvoyager.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kliachenko.gitvoyager.data.database.UsersDatabase
import com.kliachenko.gitvoyager.data.database.model.UserDbModel
import com.kliachenko.gitvoyager.data.mapper.toUserDbModel

@OptIn(ExperimentalPagingApi::class)
class UsersRemoteMediator(
    private val usersDb: UsersDatabase,
    private val apiService: ApiService
): RemoteMediator<Int, UserDbModel>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserDbModel>
    ): MediatorResult {

        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> state.lastItemOrNull()?.id
            }

            val pageSize = state.config.pageSize

            val users = apiService.getUsers(
                since = loadKey ?: 1,
                pageSize = pageSize
            )

            val usersDbModel = users.map { it.toUserDbModel() }

            usersDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    usersDb.usersDao.clearAllUsers()
                }
                usersDb.usersDao.insertAllUsers(usersDbModel)
            }

            val endOfPaginationReached = users.isEmpty()

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}