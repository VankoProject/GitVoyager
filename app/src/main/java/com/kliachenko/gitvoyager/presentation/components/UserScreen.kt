package com.kliachenko.gitvoyager.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.kliachenko.gitvoyager.presentation.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel) {



    val users = viewModel.usersPagingFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(users) { user ->
            if (user != null) {
                UserCard(user = user)
            }

        }
        val loadState = users.loadState.mediator

        item {
            if (loadState?.refresh == LoadState.Loading) {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Loading...",
                            fontSize = 32.sp
                        )
                        CircularProgressIndicator(color = Color.Red)
                    }
            }

            if (loadState?.append == LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = Color.Red)
                }
            }

            if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {

                val isPaginatingError = (loadState.append is LoadState.Error) || users.itemCount > 1

                val errorMessage = if (loadState.append is LoadState.Error)
                    (loadState.append as LoadState.Error).error.message
                else
                    (loadState.refresh as LoadState.Error).error

                val modifier = if (isPaginatingError) {
                    Modifier.padding(8.dp)
                } else {
                    Modifier.fillParentMaxSize()
                }
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (!isPaginatingError) {
                        Icon(
                            modifier = Modifier
                                .size(64.dp),
                            imageVector = Icons.Rounded.Warning, contentDescription = null
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = errorMessage.toString(),
                        textAlign = TextAlign.Center,
                    )

                    Button(
                        onClick = {
                            users.refresh()
                        },
                        content = {
                            Text(text = "Refresh")
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Gray,
                            contentColor = Color.Red,
                        )
                    )
                }
            }
        }
    }

//    if (users.loadState.refresh is LoadState.Loading) {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//        ) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    } else {
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            items(users) { user ->
//                if (user != null) {
//                    UserCard(user = user)
//                }
//            }
//
//            val loadState = users.loadState.mediator
//
//            item {
//                if (loadState?.refresh == LoadState.Loading) {
//                    RefreshData()
//                }
//            }
//
//            users.apply {
//                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item { ShowProgressBar(modifier = Modifier.fillMaxWidth()) }
//                    }
//                    loadState.append is LoadState.Loading -> {
//                        item { ShowProgressBar() }
//                    }
//                    loadState.refresh is LoadState.Error -> {
//                        val errorMessage =
//                            (loadState.refresh as LoadState.Error).error.message
//                        item { ErrorItem(message = errorMessage.toString()) }
//                    }
//                    loadState.append is LoadState.Error -> {
//                        val errorMessage =
//                            (loadState.append as LoadState.Error).error.message
//                        item { ErrorItem(message = errorMessage.toString()) }
//                    }
//                }
//            }
//        }
//    }
//}
}

@Composable
fun ShowProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        strokeWidth = 36.dp
    )
}

@Composable
fun RefreshData() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Refresh loading"
        )
        ShowProgressBar()
    }
}