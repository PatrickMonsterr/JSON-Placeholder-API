package com.example.jsonplaceholderapi.ui.screens.postdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: PostDetailViewModel = viewModel()
) {
    val post by viewModel.post.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(postId) {
        viewModel.loadPost(postId)
    }

    when {
        loading -> CircularProgressIndicator()
        error != null -> Text("Błąd: $error")
        post != null -> {
            Column(Modifier.padding(16.dp)) {
                Text(post!!.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(8.dp))
                Text(post!!.body)
                Spacer(Modifier.height(8.dp))
                Text("Autor ID: ${post!!.userId}")
            }
        }
    }
}
