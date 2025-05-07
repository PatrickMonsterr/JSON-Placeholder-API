package com.example.jsonplaceholderapi.ui.screens.userdetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jsonplaceholderapi.ui.components.TodoItem

@Composable
fun UserDetailScreen(
    userId: Int,
    viewModel: UserDetailViewModel = viewModel()
) {
    val user by viewModel.user.collectAsState()
    val todos by viewModel.todos.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadUserData(userId)
    }

    when {
        loading -> CircularProgressIndicator()
        error != null -> Text("Błąd: $error")
        user != null -> {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                item {
                    Text(user!!.name, style = MaterialTheme.typography.headlineMedium)
                    Text("Email: ${user!!.email}")
                    Text("Telefon: ${user!!.phone}")
                    Text("Firma: ${user!!.company.name}")
                    Text("Miasto: ${user!!.address.city}")
                    Spacer(Modifier.height(16.dp))
                    Text("Zadania:", style = MaterialTheme.typography.titleMedium)
                }
                items(todos) { todo ->
                    TodoItem(todo)
                }
            }
        }
    }
}
