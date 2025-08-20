package igor.second.spaceapp.appwindows.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.net.SocketTimeoutException
import java.nio.charset.StandardCharsets

data class Message(val text: String, val isMe: Boolean, val timestamp: Long = System.currentTimeMillis())
data class ConnectionState(val isConnected: Boolean, val status: String, val peerAddress: String = "")

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _connectionState = MutableStateFlow(ConnectionState(false, "Не подключено"))
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _myIpAddress = MutableStateFlow("Определение...")
    val myIpAddress: StateFlow<String> = _myIpAddress.asStateFlow()

    private var socket: DatagramSocket? = null
    private var receiverThread: Thread? = null
    private var isListening = false

    private val port = 12345
    private var targetAddress: InetAddress? = null

    init {
        viewModelScope.launch {
            startListening()
        }
    }

    fun getLocalIpAddress(context: Context) {
        viewModelScope.launch {
            try {
                // Способ 1: Через WifiManager (для Wi-Fi)
                try {
                    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                    val wifiInfo = wifiManager.connectionInfo
                    val ip = wifiInfo.ipAddress
                    if (ip != 0) {
                        val ipAddress = String.format(
                            "%d.%d.%d.%d",
                            ip and 0xff,
                            ip shr 8 and 0xff,
                            ip shr 16 and 0xff,
                            ip shr 24 and 0xff
                        )
                        _myIpAddress.value = ipAddress
                        return@launch
                    }
                } catch (e: Exception) {
                    // Продолжаем пробовать другие методы
                }

                // Способ 2: Через NetworkInterface (универсальный)
                val networkInterfaces = NetworkInterface.getNetworkInterfaces()
                while (networkInterfaces.hasMoreElements()) {
                    val networkInterface = networkInterfaces.nextElement()

                    // Пропускаем loopback и неактивные интерфейсы
                    if (networkInterface.isLoopback || !networkInterface.isUp) continue

                    val addresses = networkInterface.inetAddresses
                    while (addresses.hasMoreElements()) {
                        val address = addresses.nextElement()

                        // Берем только IPv4 адреса
                        if (!address.isLoopbackAddress && address is Inet4Address) {
                            _myIpAddress.value = address.hostAddress ?: "Неизвестно"
                            return@launch
                        }
                    }
                }

                // Способ 3: Через ConnectivityManager (для Android 21+)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val activeNetwork = connectivityManager.activeNetwork
                    val linkProperties = connectivityManager.getLinkProperties(activeNetwork)

                    linkProperties?.linkAddresses?.forEach { linkAddress ->
                        val address = linkAddress.address
                        if (address is Inet4Address && !address.isLoopbackAddress) {
                            _myIpAddress.value = address.hostAddress ?: "Неизвестно"
                            return@launch
                        }
                    }
                }

                // Если все методы не сработали
                _myIpAddress.value = "Не удалось определить"

            } catch (e: Exception) {
                _myIpAddress.value = "Ошибка: ${e.message}"
            }
        }
    }

    fun refreshIpAddress(context: Context) {
        _myIpAddress.value = "Определение..."
        getLocalIpAddress(context)
    }

    fun connectToPeer(ip: String) {
        viewModelScope.launch {
            try {
                targetAddress = InetAddress.getByName(ip)
                _connectionState.value = ConnectionState(true, "Подключено к $ip", ip)
                sendMessage("Привет! Я ${_myIpAddress.value}")
            } catch (e: Exception) {
                _connectionState.value = ConnectionState(false, "Ошибка подключения: ${e.message}")
            }
        }
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        viewModelScope.launch {
            try {
                val message = Message(text, true)
                _messages.value = _messages.value + message

                targetAddress?.let { address ->
                    val data = text.toByteArray(StandardCharsets.UTF_8)
                    val packet = DatagramPacket(data, data.size, address, port)
                    socket?.send(packet)
                }
            } catch (e: Exception) {
                _connectionState.value = ConnectionState(false, "Ошибка отправки: ${e.message}")
            }
        }
    }

    private fun startListening() {
        if (isListening) return

        viewModelScope.launch {
            try {
                socket = DatagramSocket(port)
                socket?.soTimeout = 5000 // Таймаут 5 секунд
                socket?.broadcast = true
                isListening = true

                _connectionState.value = ConnectionState(false, "Ожидание сообщений...")

                receiverThread = Thread {
                    val buffer = ByteArray(1024)
                    while (isListening) {
                        try {
                            val packet = DatagramPacket(buffer, buffer.size)
                            socket?.receive(packet)

                            val messageText = String(packet.data, 0, packet.length, StandardCharsets.UTF_8)
                            val senderAddress = packet.address.hostAddress

                            // Если это первое сообщение от пира, устанавливаем соединение
                            if (targetAddress == null) {
                                targetAddress = packet.address
                                _connectionState.value = ConnectionState(true, "Подключено к $senderAddress", senderAddress)
                            }

                            viewModelScope.launch {
                                val message = Message(messageText, false, System.currentTimeMillis())
                                _messages.value = _messages.value + message
                            }
                        } catch (e: SocketTimeoutException) {
                            // Таймаут - нормальная ситуация, продолжаем слушать
                            continue
                        } catch (e: SocketException) {
                            if (isListening) {
                                _connectionState.value = ConnectionState(false, "Ошибка приема: ${e.message}")
                            }
                        } catch (e: IOException) {
                            _connectionState.value = ConnectionState(false, "Ошибка ввода-вывода: ${e.message}")
                        }
                    }
                }.apply { start() }

            } catch (e: Exception) {
                _connectionState.value = ConnectionState(false, "Ошибка запуска сервера: ${e.message}")
            }
        }
    }

    fun disconnect() {
        isListening = false
        receiverThread?.interrupt()
        socket?.close()
        targetAddress = null
        _connectionState.value = ConnectionState(false, "Отключено")
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainConnection() {
    val viewModel: ChatViewModel = viewModel()
    val context = LocalContext.current

    // Автоматически определяем IP при запуске
    LaunchedEffect(Unit) {
        viewModel.getLocalIpAddress(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Прямой чат") },
                actions = {
                    ConnectionStatus(viewModel, context)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MyIpAddress(viewModel, context)
            MessagesList(viewModel)
            MessageInput(viewModel)
        }
    }
}

@Composable
fun MyIpAddress(viewModel: ChatViewModel, context: Context) {
    val myIpAddress by viewModel.myIpAddress.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { viewModel.refreshIpAddress(context) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Мой IP",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Мой IP: $myIpAddress",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Обновить",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun ConnectionStatus(viewModel: ChatViewModel, context: Context) {
    val connectionState by viewModel.connectionState.collectAsState()
    val myIpAddress by viewModel.myIpAddress.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var peerIp by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Подключение к собеседнику") },
            text = {
                Column {
                    Text(
                        "Мой IP: $myIpAddress",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = peerIp,
                        onValueChange = { peerIp = it },
                        label = { Text("IP адрес собеседника") },
                        placeholder = { Text("192.168.1.2") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Убедитесь, что оба устройства в одной сети Wi-Fi",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (peerIp.isNotBlank()) {
                            viewModel.connectToPeer(peerIp)
                            showDialog = false
                        }
                    },
                    enabled = peerIp.isNotBlank()
                ) {
                    Text("Подключиться")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }

    IconButton(onClick = { showDialog = true }) {
        Icon(
            imageVector = if (connectionState.isConnected) Icons.Default.Check else Icons.Default.PlayArrow,
            contentDescription = "Статус подключения",
            tint = if (connectionState.isConnected) Color.Green else MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun MessagesList(viewModel: ChatViewModel) {
    val messages by viewModel.messages.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(8.dp),
        reverseLayout = true
    ) {
        items(messages.reversed()) { message ->
            MessageBubble(message)
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = if (message.isMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isMe) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(16.dp),
                color = if (message.isMe) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun MessageInput(viewModel: ChatViewModel) {
    var messageText by remember { mutableStateOf("") }
    val connectionState by viewModel.connectionState.collectAsState()

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = messageText,
            onValueChange = { messageText = it },
            modifier = Modifier.weight(1f),
            placeholder = {
                Text(
                    if (connectionState.isConnected) "Введите сообщение..."
                    else "Сначала подключитесь к собеседнику"
                )
            },
            singleLine = true,
            enabled = connectionState.isConnected
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                viewModel.sendMessage(messageText)
                messageText = ""
            },
            enabled = messageText.isNotBlank() && connectionState.isConnected
        ) {
            Icon(Icons.Default.Send, "Отправить")
        }
    }
}