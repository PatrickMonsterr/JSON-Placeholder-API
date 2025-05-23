package com.example.jsonplaceholderapi.ui.screens.userdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jsonplaceholderapi.ui.components.TodoItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jsonplaceholderapi.ui.map.GoogleMapView

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
        loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))

        error != null -> Text("Błąd: $error", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))

        user != null -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color(0xFF121212), Color(0xFF1E1E2F))))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3C))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(user!!.name, style = MaterialTheme.typography.headlineMedium, color = Color.White)
                            Spacer(Modifier.height(4.dp))
                            Text("Email: ${user!!.email}", color = Color.LightGray)
                            Text("Telefon: ${user!!.phone}", color = Color.LightGray)
                            Text("Firma: ${user!!.company.name}", color = Color.LightGray)
                            Text("Miasto: ${user!!.address.city}", color = Color.LightGray)
                            GoogleMapView(
                                lat = user!!.address.geo.lat.toDouble(),
                                lng = user!!.address.geo.lng.toDouble(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Text("Zadania:", style = MaterialTheme.typography.titleLarge, color = Color.White)
                }

                items(todos) { todo ->
                    TodoItem(todo)
                }
            }
        }
    }
}


//@Composable
//fun MapView(lat: String, lng: String) {
//    val htmlData = """
//        <html>
//        <body style="margin:0;">
//            <iframe
//                width="100%"
//                height="100%"
//                frameborder="0"
//                style="border:0"
//                src="https://maps.google.com/maps?q=$lat,$lng&z=15&output=embed"
//                allowfullscreen>
//            </iframe>
//        </body>
//        </html>
//    """.trimIndent()
//
//    AndroidView(
//        factory = { context ->
//            WebView(context).apply {
//                layoutParams = android.view.ViewGroup.LayoutParams(
//                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
//                    600
//                )
//                webViewClient = WebViewClient()
//                settings.javaScriptEnabled = true
//                settings.domStorageEnabled = true
//                setBackgroundColor(android.graphics.Color.WHITE)
//                loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)
//            }
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(400.dp)
//    )
//
//}




