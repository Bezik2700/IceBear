package igor.second.spaceapp.appwindows.cardStartScreen.ratingSetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import igor.second.spaceapp.appwindows.gameProcess.settings.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RatingViewModel : ViewModel() {
    private val repository = Repository()

    private val _userNames = MutableStateFlow<List<String>>(emptyList())
    val userNames: StateFlow<List<String>> = _userNames.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadUserNames()
    }

    fun addUserToRating(userName: String) {
        viewModelScope.launch {
            repository.addUserToRating(
                userName = userName,
                onSuccess = {
                    // Перезагружаем список имен после добавления
                    loadUserNames()
                },
                onError = { errorMessage ->
                    _error.value = "Ошибка добавления в рейтинг: $errorMessage"
                }
            )
        }
    }

    fun loadUserNames() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            repository.loadUserNames(
                onSuccess = { names ->
                    _userNames.value = names
                    _isLoading.value = false
                },
                onError = { errorMessage ->
                    _error.value = errorMessage
                    _isLoading.value = false
                    _userNames.value = listOf("Иван", "Мария", "Петр")
                }
            )
        }
    }

    fun clearError() {
        _error.value = null
    }
}