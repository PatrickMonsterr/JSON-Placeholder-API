package com.example.jsonplaceholderapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.jsonplaceholderapi.ui.screens.main.MainScreen
import com.example.jsonplaceholderapi.ui.screens.postdetails.PostDetailScreen
import com.example.jsonplaceholderapi.ui.screens.userdetails.UserDetailScreen
import com.example.jsonplaceholderapi.ui.screens.ProfileScreen.ProfileScreen

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object PostDetail : Screen("post_detail/{postId}") {
        fun createRoute(postId: Int) = "post_detail/$postId"
    }
    object UserDetail : Screen("user_detail/{userId}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }
    object Profile : Screen("profile")

}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(
                onPostClick = { postId ->
                    navController.navigate(Screen.PostDetail.createRoute(postId))
                },
                onUserClick = { userId ->
                    navController.navigate(Screen.UserDetail.createRoute(userId))
                },
                onProfileClick = {navController.navigate(Screen.Profile.route)}
            )
        }
        composable(
            route = Screen.PostDetail.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: return@composable
            PostDetailScreen(postId = postId)
        }
        composable(
            route = Screen.UserDetail.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: return@composable
            UserDetailScreen(userId = userId)
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
