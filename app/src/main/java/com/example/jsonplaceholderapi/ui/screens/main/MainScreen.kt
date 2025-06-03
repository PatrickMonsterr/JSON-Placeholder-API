package com.example.jsonplaceholderapi.ui.screens.main

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jsonplaceholderapi.data.model.PostWithUser
import com.example.jsonplaceholderapi.ui.components.PostItem
import com.example.jsonplaceholderapi.ui.screens.ProfileScreen.UserPreferences

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

    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val firstName by userPrefs.firstNameFlow.collectAsState(initial = null)
    val lastName by userPrefs.lastNameFlow.collectAsState(initial = null)

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
                        if (!firstName.isNullOrBlank() || !lastName.isNullOrBlank()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Zalogowany użytkownik",
                                    tint = Color.White
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "Zalogowano jako: $firstName $lastName",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
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