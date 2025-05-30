package com.example.jsonplaceholderapi.ui.screens.main

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jsonplaceholderapi.data.model.PostWithUser
import com.example.jsonplaceholderapi.ui.components.PostItem

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    onPostClick: (Int) -> Unit,
    onUserClick: (Int) -> Unit,
    onProfileClick: () -> Unit
) {
    val posts by viewModel.posts.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF121212), Color(0xFF1E1E2F))
                )
            )
    ) {
        when {
            loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            error != null -> Text(
                "Błąd: $error",
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Center)
            )

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            "Posty",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Button(onClick = onProfileClick) {
                            Text("Mój Profil")
                        }
                    }


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
}


