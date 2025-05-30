package com.example.jsonplaceholderapi.ui.screens.postdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF121212), Color(0xFF1E1E2F))))
            .padding(16.dp)
    ) {
        when {
            loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            error != null -> Text(
                "Błąd: $error",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge
            )

            post != null -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3C))
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            post!!.title,
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            post!!.body,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.LightGray
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Autor ID: ${post!!.userId}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFF9DB3FF)
                        )
                    }
                }
            }
        }
    }
}



