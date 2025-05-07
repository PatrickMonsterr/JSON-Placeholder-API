package com.example.jsonplaceholderapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jsonplaceholderapi.navigation.AppNavGraph
import com.example.jsonplaceholderapi.ui.theme.JSONPlaceholderAPITheme
import com.example.jsonplaceholderapi.ui.theme.JSONPlaceholderAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JSONPlaceholderAPITheme {
                AppNavGraph()
            }
        }
    }
}
