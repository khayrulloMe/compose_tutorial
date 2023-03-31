package uz.gita.compose_tutorial

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MainViewModel {
    val uiState: StateFlow<MainUiState>
    fun onEventDispatcher(intent: MainIntent):Job
}

sealed interface MainIntent {
    data class Increase(val count: Int = 0) : MainIntent
    data class Decrease(val count: Int = 0) : MainIntent
}

sealed class MainUiState {
    data class Loading(
        val isLoading: Boolean = false,
    ) : MainUiState()

    data class Success(
        val count: Int = 0
    ) : MainUiState()

    data class Fail(
        val Message: String = ""
    ) : MainUiState()
}