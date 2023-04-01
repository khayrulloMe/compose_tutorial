package uz.gita.compose_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import uz.gita.compose_tutorial.impl.MainViewModelImpl
import uz.gita.compose_tutorial.ui.theme.Compose_tutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_tutorialTheme {
                val viewModel: MainViewModel = getViewModel<MainViewModelImpl>()
                val uiState = viewModel.uiState.collectAsState()
                MainContent(uiState.value, viewModel::onEventDispatcher)
            }
        }
    }
}

@Composable
fun MainContent(uiState: MainUiState, onEvent: (intent: MainIntent) -> Unit) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(onClick = {
            onEvent(MainIntent.Increase(count))

        }
        ) {
            Text(text = "+", fontSize = 32.sp, modifier = Modifier)
        }
        when (uiState) {
            is MainUiState.Fail -> {
                Text(text = uiState.Message, color = MaterialTheme.colorScheme.error, fontSize = 32.sp)
            }
            is MainUiState.Loading -> {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                }
            }
            is MainUiState.Success -> {
                count = uiState.count
                Text(text = uiState.count.toString(), fontSize = 48.sp)
            }
        }
        Button(onClick = { onEvent(MainIntent.Decrease(count)) }) {
            Text(text = "-", fontSize = 32.sp)
        }
    }

}