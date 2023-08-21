package com.kliachenko.gitvoyager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kliachenko.gitvoyager.presentation.UserViewModel
import com.kliachenko.utils.Resource

@Composable
fun UserScreen(viewModel: UserViewModel) {

    val usersList by viewModel.users.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (val resource = usersList) {
            is Resource.Success -> {
                val users = resource.data
                LazyColumn {
                    items(users ?: emptyList()) { user ->
                        UserCard(user = user)
                    }
                }
            }
            is Resource.Error -> {
                val errorMessage = resource.message
                Text(text = errorMessage.toString())
            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                    strokeWidth = ProgressIndicatorDefaults.StrokeWidth
                )
            }
        }
    }
}
