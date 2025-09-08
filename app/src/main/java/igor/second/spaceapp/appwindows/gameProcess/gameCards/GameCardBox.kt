package igor.second.spaceapp.appwindows.gameProcess.gameCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.GameMiniCard
import igor.second.spaceapp.appwindows.gameProcess.gameCards.logic.cardNumber
import igor.second.spaceapp.appwindows.gameProcess.gameCards.logic.cardScoreFun
import igor.second.spaceapp.appwindows.gameProcess.gameCards.logic.cardValueForGame
import igor.second.spaceapp.appwindows.gameProcess.gameCards.logic.gameProcessStart
import igor.second.spaceapp.appwindows.gameProcess.gameCards.logic.messageTextGenerate
import igor.second.spaceapp.appwindows.gameProcess.settings.Message
import igor.second.spaceapp.appwindows.gameProcess.settings.Repository
import kotlinx.coroutines.launch

@Composable
fun GameCardBox(
    sliderPosition: MutableState<Float>,
    bronzeValue1: MutableState<Int>,
    bronzeValue2: MutableState<Int>,
    bronzeValue3: MutableState<Int>,
    bronzeValue4: MutableState<Int>,
    bronzeValue5: MutableState<Int>,
    bronzeValue6: MutableState<Int>,
    bronzeValue7: MutableState<Int>,
    bronzeValue8: MutableState<Int>,
    silverValue1: MutableState<Int>,
    silverValue2: MutableState<Int>,
    silverValue3: MutableState<Int>,
    silverValue4: MutableState<Int>,
    silverValue5: MutableState<Int>,
    silverValue6: MutableState<Int>,
    silverValue7: MutableState<Int>,
    silverValue8: MutableState<Int>,
    goldValue1: MutableState<Int>,
    goldValue2: MutableState<Int>,
    goldValue3: MutableState<Int>,
    goldValue4: MutableState<Int>,
    goldValue5: MutableState<Int>,
    goldValue6: MutableState<Int>,
    goldValue7: MutableState<Int>,
    goldValue8: MutableState<Int>,
    diamondValue1: MutableState<Int>,
    diamondValue2: MutableState<Int>,
    diamondValue3: MutableState<Int>,
    diamondValue4: MutableState<Int>,
    diamondValue5: MutableState<Int>,
    diamondValue6: MutableState<Int>,
    diamondValue7: MutableState<Int>,
    diamondValue8: MutableState<Int>,
    platinumValue1: MutableState<Int>,
    platinumValue2: MutableState<Int>,
    platinumValue3: MutableState<Int>,
    platinumValue4: MutableState<Int>,
    platinumValue5: MutableState<Int>,
    platinumValue6: MutableState<Int>,
    platinumValue7: MutableState<Int>,
    platinumValue8: MutableState<Int>,
    epicValue1: MutableState<Int>,
    epicValue2: MutableState<Int>,
    epicValue3: MutableState<Int>,
    epicValue4: MutableState<Int>,
    epicValue5: MutableState<Int>,
    epicValue6: MutableState<Int>,
    epicValue7: MutableState<Int>,
    epicValue8: MutableState<Int>,
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
    dataStoreManager: DataStoreManager
){

    val userName = remember { "User${(1000..9999).random()}" }
    val coroutineScope = rememberCoroutineScope()
    val repository = remember { Repository() }
    var isSending by remember { mutableStateOf(false) }
    var messageText by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    var context = LocalContext.current

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

    Box(modifier = Modifier){

        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 108.dp, start = 64.dp)
        ) {
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 1,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 1)
                    )

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 1
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 5),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 1,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 2,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 2))

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 2
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 6),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 2,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 3,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 3))

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 3
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 7),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 3,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 4,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 4))

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 4
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 8),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 4,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
        }
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 48.dp)
        ) {
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 5,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 5))

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 5
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 1),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 5,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 6,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 6))

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 6
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 2),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 6,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 7,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 7))

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 7
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 3),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 7,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
            GameMiniCard(
                onClick = {
                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = 8,
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8
                    )

                    messageText = context.getString(messageTextGenerate(
                        sliderPosition = sliderPosition,
                        cardNumber = 8)
                    )

                    if (messageText.isNotBlank() && !isSending) {
                        isSending = true
                        val message = Message(
                            content = messageText,
                            sender_name = userName,
                            card_value = cardValueForGame(
                                sliderPosition = sliderPosition,
                                cardNumber = 8
                            )
                        )
                        coroutineScope.launch {
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText = ""
                                    isSending = false
                                    loadMessages()
                                },
                                onError = { errorMessage ->
                                    error = errorMessage
                                    isSending = false
                                }
                            )
                        }
                    }
                },
                image = cardNumber(sliderPosition = sliderPosition, cardValue = 4),
                cardScore = cardScoreFun(
                    sliderPosition = sliderPosition,
                    cardNumber = 8,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8
                )
            )
        }
    }
}