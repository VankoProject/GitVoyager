package com.kliachenko.gitvoyager.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kliachenko.gitvoyager.domain.model.User
import com.kliachenko.gitvoyager.domain.usecases.GetAllUsersUseCase
import com.kliachenko.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    val usersPagingFlow: Flow<PagingData<User>> = getAllUsersUseCase.getAllUsers()
        .cachedIn(viewModelScope)
}