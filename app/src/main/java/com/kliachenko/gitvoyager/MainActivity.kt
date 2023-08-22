package com.kliachenko.gitvoyager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kliachenko.gitvoyager.presentation.UserViewModel
import com.kliachenko.gitvoyager.presentation.components.UserScreen
import com.kliachenko.ui.theme.GitVoyagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitVoyagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    val viewModel = hiltViewModel<UserViewModel>()
                    UserScreen(viewModel)
                }
            }
        }
    }
}