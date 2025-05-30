import com.example.jsonplaceholderapi.data.model.Post
import com.example.jsonplaceholderapi.data.model.Todo
import com.example.jsonplaceholderapi.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{postId}")
    suspend fun getPostById(@Path("postId") postId: Int): Post

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): User

    @GET("todos")
    suspend fun getTodosByUser(@Query("userId") userId: Int): List<Todo>
}
