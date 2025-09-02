package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.appwindows.gameProcess.MessageUI
import igor.second.spaceapp.appwindows.gameProcess.settings.Message
import igor.second.spaceapp.appwindows.gameProcess.settings.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChatCards(modifier: Modifier = Modifier){

    val userName = remember { "User${(1000..9999).random()}" }
    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
    val repository = remember { Repository() }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    fun loadMessages() {
        coroutineScope.launch {
            repository.loadMessages(
                onSuccess = { loadedMessages ->
                    messages = loadedMessages
                    isLoading = false
                    error = null
                },
                onError = { errorMessage ->
                    error = errorMessage
                    isLoading = false
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        loadMessages()
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            loadMessages()
        }
    }

    Card (
        modifier = modifier
            .fillMaxHeight(0.4f)
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageUI(
                    message = message,
                    isMyMessage = message.sender_name == userName
                )
            }
        }
    }
}