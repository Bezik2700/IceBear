package igor.second.spaceapp.appwindows.gameProcess

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.appwindows.gameProcess.settings.Message
import igor.second.spaceapp.appwindows.gameProcess.settings.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainConnection() {

    val repository = remember { Repository() }
    val coroutineScope = rememberCoroutineScope()

    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
    var newMessageText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    var isSending by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val userName = remember { "User${(1000..9999).random()}" }

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Простой чат") },
                actions = {
                    Text("Я: $userName")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
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
            } else {
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

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = newMessageText,
                        onValueChange = { newMessageText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Введите сообщение...") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (newMessageText.isNotBlank() && !isSending) {
                                isSending = true
                                val message = Message(
                                    content = newMessageText,
                                    sender_name = userName
                                )
                                coroutineScope.launch {
                                    repository.sendMessage(
                                        message = message,
                                        onSuccess = {
                                            newMessageText = ""
                                            isSending = false
                                            loadMessages() // Обновляем список
                                        },
                                        onError = { errorMessage ->
                                            error = errorMessage
                                            isSending = false
                                        }
                                    )
                                }
                            }
                        },
                        enabled = newMessageText.isNotBlank() && !isSending
                    ) {
                        if (isSending) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Отпр")
                        }
                    }
                }
            }
        }
    }
}
