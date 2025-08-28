package igor.second.spaceapp.appsettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {

    private val _timer = MutableStateFlow(10)
    val timer: StateFlow<Int> = _timer

    private val _timerEnabled = MutableStateFlow(false)
    val timerEnabled: StateFlow<Boolean> = _timerEnabled

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

    fun timerEnabledChange(){
        viewModelScope.launch {
            _timerEnabled.value = !_timerEnabled.value
        }
    }
}