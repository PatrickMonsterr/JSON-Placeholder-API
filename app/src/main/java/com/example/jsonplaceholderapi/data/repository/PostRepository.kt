package com.example.jsonplaceholderapi.data.repository
import com.example.jsonplaceholderapi.data.model.*
import com.example.jsonplaceholderapi.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PostRepository {

    private val api = RetrofitClient.apiService

    suspend fun getAllPosts(): List<Post> = withContext(Dispatchers.IO) {
        api.getPosts()
    }

    suspend fun getAllUsers(): List<User> = withContext(Dispatchers.IO) {
        api.getUsers()
    }

    suspend fun getPostById(postId: Int): Post = withContext(Dispatchers.IO) {
        api.getPostById(postId)
    }

    suspend fun getUserById(userId: Int): User = withContext(Dispatchers.IO) {
        api.getUserById(userId)
    }

    suspend fun getTodosByUser(userId: Int): List<Todo> = withContext(Dispatchers.IO) {
        api.getTodosByUser(userId)
    }

    suspend fun getPostsWithUsers(): List<PostWithUser> = withContext(Dispatchers.IO) {
        val posts = api.getPosts()
        val users = api.getUsers()
        val userMap = users.associateBy { it.id }
        posts.mapNotNull { post ->
            userMap[post.userId]?.let { user ->
                PostWithUser(post, user)
            }
        }
    }
}
