package uz.gita.compose_tutorial.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.compose_tutorial.MainIntent
import uz.gita.compose_tutorial.MainUiState
import uz.gita.compose_tutorial.MainViewModel

class MainViewModelImpl : MainViewModel, ViewModel() {
    override val uiState = MutableStateFlow<MainUiState>(MainUiState.Loading())
    init {
        viewModelScope.launch {
            reduce {
                MainUiState.Loading().copy(isLoading = true)
            }
            delay(1000)
            reduce {
                MainUiState.Loading().copy(isLoading = false)
            }
            reduce {
                MainUiState.Success().copy(count = 0)
            }
        }
    }
    override fun onEventDispatcher(intent: MainIntent) = viewModelScope.launch {
        when (intent) {
            is MainIntent.Decrease -> {
                reduce {
                    MainUiState.Success().copy(count = intent.count - 1)
                }
            }
            is MainIntent.Increase -> {
                reduce {
                    MainUiState.Success().copy(count = intent.count + 1)
                }

            }
        }
    }
    private fun reduce(block: (MainUiState) -> MainUiState) {
        val old = uiState.value
        val new = block(old)
        uiState.tryEmit(new)
    }

}