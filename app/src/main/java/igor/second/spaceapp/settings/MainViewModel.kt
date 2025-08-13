package igor.second.spaceapp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _timer = MutableStateFlow(10)
    val timer: StateFlow<Int> = _timer

    private val _cardValue = MutableStateFlow(0)
    val cardValue: StateFlow<Int> = _cardValue

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

    fun cardValuePlus(){
        viewModelScope.launch {
            _cardValue.value += 1
        }
    }

    fun cardValueMinus(){
        viewModelScope.launch {
            _cardValue.value -= 1
        }
    }

    fun cardValueZero(){
        viewModelScope.launch {
            _cardValue.value = 0
        }
    }
}