package com.example.jsonplaceholderapi.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jsonplaceholderapi.data.model.PostWithUser
import com.example.jsonplaceholderapi.ui.components.PostItem

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    onPostClick: (Int) -> Unit,
    onUserClick: (Int) -> Unit
) {
    val posts by viewModel.posts.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    when {
        loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Błąd: $error")
            }
        }
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(posts) { postWithUser ->
                    PostItem(
                        postWithUser = postWithUser,
                        onPostClick = { onPostClick(postWithUser.post.id) },
                        onUserClick = { onUserClick(postWithUser.user.id) }
                    )
                }
            }
        }
    }
}
