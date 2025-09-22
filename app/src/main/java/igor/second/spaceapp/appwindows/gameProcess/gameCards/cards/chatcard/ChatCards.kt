package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.chatcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
) {
    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val loadMessages: () -> Unit = {
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

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.20f)
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    isLoading -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFF9678B6).copy(alpha = 0.6f))
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                strokeWidth = 2.dp,
                                color = Color(0xFFEA2264)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = stringResource(R.string.load_messages),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFFEA2264)
                            )
                        }
                    }

                    error != null -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFF9678B6).copy(alpha = 0.6f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Error",
                                tint = Color(0xFFEA2264),
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = stringResource(R.string.error_messages),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFEA2264)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { loadMessages() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFEA2264),
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier.height(36.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.refresh),
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }

                    messages.isEmpty() -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFF9678B6).copy(alpha = 0.6f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Empty chat",
                                tint = Color(0xFFEA2264),
                                modifier = Modifier.size(40.dp).padding(bottom = 4.dp)
                            )
                            Text(
                                text = stringResource(R.string.starting),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFEA2264),
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFF9678B6).copy(alpha = 0.6f)),
                            reverseLayout = true,
                            verticalArrangement = Arrangement.Center
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
        }
    }
}