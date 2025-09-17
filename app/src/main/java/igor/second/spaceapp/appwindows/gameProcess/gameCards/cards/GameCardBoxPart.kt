package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appwindows.cardStartScreen.ratingSetting.RatingViewModel
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.bigcard.cardValueForGame
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.GameMiniCard
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.logic.cardNumber
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.logic.cardScoreFun
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.logic.gameProcessStart
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.logic.messageTextGenerate
import igor.second.spaceapp.appwindows.gameProcess.settings.Message
import igor.second.spaceapp.appwindows.gameProcess.settings.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GameCardBoxPart(
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
    userName: MutableState<String>,
    dataStoreManager: DataStoreManager,
    lastCardValue: String,
    secondLastCardValue: String,
    thirdLastCardValue: String,
    coroutineScope: CoroutineScope,
    isSending: MutableState<Boolean>,
    messageText: MutableState<String>,
    number: Int,
    repository: Repository,
    error: MutableState<String?>,
    enabledForProgress: MutableState<Boolean>,
    ratingViewModel: RatingViewModel = viewModel()
){

    var context = LocalContext.current

    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var wasChatCleared by remember { mutableStateOf(false) }
    var isClearing by remember { mutableStateOf(false) }

    fun loadMessages() {
        coroutineScope.launch {
            repository.loadMessages(
                onSuccess = { loadedMessages ->
                    messages = loadedMessages
                    isLoading = false
                    error.value = null
                },
                onError = { errorMessage ->
                    error.value = errorMessage
                    isLoading = false
                }
            )
        }
    }

    fun clearChatFromServer(callback: (Boolean) -> Unit = {}) {
        coroutineScope.launch {
            isClearing = true

            ratingViewModel.addUserToRating(userName.value)

            repository.deleteAllMessages(
                onSuccess = {
                    messages = emptyList()
                    wasChatCleared = true
                    isClearing = false
                    callback(true)
                    loadMessages()
                },
                onError = { errorMessage ->
                    error.value = "Ошибка очистки: $errorMessage"
                    isClearing = false
                    callback(false)
                }
            )
        }
    }

    GameMiniCard(
        onClick = {
            coroutineScope.launch {

                if (isClearing) {
                    Toast.makeText(context, "Идет очистка чата, подождите", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val currentCardValue = cardValueForGame(
                    sliderPosition = sliderPosition,
                    cardNumber = number
                )

                if (lastCardValue.toInt() - 30 <= currentCardValue &&
                    lastCardValue.toInt() + 30 >= currentCardValue) {

                    val cardValueToSend = currentCardValue
                    val messageContent = context.getString(
                        messageTextGenerate(
                            sliderPosition = sliderPosition,
                            cardNumber = number
                        )
                    )

                    gameProcessStart(
                        dataStoreManager = dataStoreManager,
                        userGenerationLevel = userGenerationLevel,
                        userMoneyValue = userMoneyValue,
                        sliderPosition = sliderPosition,
                        cardNumber = number,
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
                        epicValue8 = epicValue8,
                        userName = userName
                    )

                    val shouldClearChat = lastCardValue == secondLastCardValue &&
                            lastCardValue == thirdLastCardValue &&
                            lastCardValue.toInt() != 0 &&
                            secondLastCardValue.toInt() != 0 &&
                            thirdLastCardValue.toInt() != 0

                    if (shouldClearChat) {
                        clearChatFromServer { success ->
                            if (success && messageContent.isNotBlank() && !isSending.value) {
                                isSending.value = true
                                val message = Message(
                                    content = messageContent,
                                    sender_name = userName.value,
                                    card_value = cardValueToSend
                                )
                                repository.sendMessage(
                                    message = message,
                                    onSuccess = {
                                        messageText.value = ""
                                        isSending.value = false
                                        wasChatCleared = false
                                    },
                                    onError = { errorMessage ->
                                        error.value = errorMessage
                                        isSending.value = false
                                        wasChatCleared = false
                                    }
                                )
                            }
                        }
                    } else {
                        // Обычная отправка без очистки
                        if (messageContent.isNotBlank() && !isSending.value) {
                            isSending.value = true
                            val message = Message(
                                content = messageContent,
                                sender_name = userName.value,
                                card_value = cardValueToSend
                            )
                            repository.sendMessage(
                                message = message,
                                onSuccess = {
                                    messageText.value = ""
                                    isSending.value = false
                                },
                                onError = { errorMessage ->
                                    error.value = errorMessage
                                    isSending.value = false
                                }
                            )
                        }
                    }
                    enabledForProgress.value = true
                } else {
                    Toast.makeText(context, "not", Toast.LENGTH_SHORT).show()
                }
            }
        },
        image = cardNumber(sliderPosition = sliderPosition, cardValue = number),
        cardScore = cardScoreFun(
            sliderPosition = sliderPosition,
            cardNumber = number,
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
        ),
        cardValue = cardValueForGame(
            sliderPosition = sliderPosition,
            cardNumber = number
        )
    )
}