package com.example.jsonplaceholderapi.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jsonplaceholderapi.data.model.PostWithUser

@Composable
fun PostItem(
    postWithUser: PostWithUser,
    onPostClick: () -> Unit,
    onUserClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onPostClick() }
            .animateContentSize(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3C))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = postWithUser.post.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Autor: ${postWithUser.user.name}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF9DB3FF)
                ),
                modifier = Modifier.clickable { onUserClick() }
            )
        }
    }
}


