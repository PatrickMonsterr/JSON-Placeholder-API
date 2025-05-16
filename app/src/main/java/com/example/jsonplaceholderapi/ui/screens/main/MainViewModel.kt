import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.model.PostWithUser
import com.example.jsonplaceholderapi.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostRepository = PostRepository()) : ViewModel() {

    private val _posts = MutableStateFlow<List<PostWithUser>>(emptyList())
    val posts: StateFlow<List<PostWithUser>> = _posts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            println("START")
            _isLoading.value = true
            _error.value = null
            try {
                _posts.value = repository.getPostsWithUsers()
                println("SUCCESS")
            } catch (e: Exception) {
                println("ERROR: ${e.message}")
                _error.value = "Failed to load data. Please check your internet connection."
            } finally {
                println("FINALLY")
                _isLoading.value = false
            }
        }
    }

}

