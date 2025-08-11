package igor.second.spaceapp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _timer = MutableStateFlow(10)
    val timer: StateFlow<Int> = _timer

    fun timerLoad(){
        viewModelScope.launch {
            _timer.value -= 1
        }
    }

    fun timerRestart(){
        viewModelScope.launch {
            _timer.value = 10
        }
    }
}