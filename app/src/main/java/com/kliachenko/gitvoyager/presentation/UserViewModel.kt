package com.kliachenko.gitvoyager.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kliachenko.gitvoyager.domain.model.User
import com.kliachenko.gitvoyager.domain.usecases.GetUsersUseCase
import com.kliachenko.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val getUserUseCase: GetUsersUseCase): ViewModel() {

    private val _users = MutableStateFlow<Resource<List<User>>>(Resource.Loading())
    val users: StateFlow<Resource<List<User>>> = _users

    init {
        loadUsers(0)
    }

    private fun loadUsers(since: Int) {
        viewModelScope.launch {
            _users.value = Resource.Loading()
            try {
                getUserUseCase(since).collect { result ->
                    _users.value = result
                }
            } catch (e: Exception) {
                _users.value = Resource.Error("An error occurred")
            }
        }
    }

}