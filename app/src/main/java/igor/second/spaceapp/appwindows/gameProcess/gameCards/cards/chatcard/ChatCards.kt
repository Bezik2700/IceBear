package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.chatcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.second.spaceapp.R
import igor.second.spaceapp.appwindows.gameProcess.settings.Message
import igor.second.spaceapp.appwindows.gameProcess.settings.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChatCards(
    userName: String,
    repository: Repository
){

    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
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
        while (true) {
            delay(1000)
            loadMessages()
        }
    }

    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Загрузка сообщений...")
                }
            }
        } else if (error != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ошибка:", color = MaterialTheme.colorScheme.error)
                    Text(error!!, color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { loadMessages() }) {
                        Text("Повторить")
                    }
                }
            }
        } else if(messages.isEmpty()){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 32.dp, end = 32.dp)
            ) {
                Text(
                    stringResource(R.string.starting),
                    fontSize = 24.sp
                    )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
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
}