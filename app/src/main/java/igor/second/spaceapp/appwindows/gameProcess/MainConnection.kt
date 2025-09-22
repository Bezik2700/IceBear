package igor.second.spaceapp.appwindows.gameProcess

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.MainViewModel
import igor.second.spaceapp.appsettings.network.IsNotOnlineDialog
import igor.second.spaceapp.appsettings.network.NetworkUtils
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardInformation.MainInformation
import igor.second.spaceapp.appwindows.gameProcess.gameCards.GameCardBox
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.CustomSlider
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.bigcard.GameBigCard
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.bigcard.bigCardImage
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.chatcard.ChatCards
import igor.second.spaceapp.appwindows.gameProcess.settings.Message
import igor.second.spaceapp.appwindows.gameProcess.settings.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainConnection(
    modifier: Modifier = Modifier,
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
    userName: MutableState<String>,
    userMoneyValue: MutableState<Int>,
    userGenerationLevel: MutableState<Int>,
    dataStoreManager: DataStoreManager,
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController
){
    var informationAboutGame = remember { mutableStateOf(false) }

    var sliderPosition = remember { mutableFloatStateOf(1f) }

    val context = LocalContext.current

    val isOnline by mainViewModel.isOnline.collectAsState()

    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }

    val repository = remember { Repository() }

    val coroutineScope = rememberCoroutineScope()

    var isLoading = remember { mutableStateOf(true) }

    var isSending = remember { mutableStateOf(false) }

    var error = remember { mutableStateOf<String?>("") }

    var messageText = remember { mutableStateOf("") }

    val thirdLastMessage = remember(messages) {
        messages.sortedByDescending { it.created_at ?: "" }.getOrNull(2) }
    val thirdLastCardValue = remember(thirdLastMessage) {
        thirdLastMessage?.card_value ?: 0 }

    val secondLastMessage = remember(messages) {
        messages.sortedByDescending { it.created_at ?: "" }.getOrNull(1) }
    val secondLastCardValue = remember(secondLastMessage) {
        secondLastMessage?.card_value ?: 0 }

    val lastMessage = remember(messages) {
        messages.maxByOrNull { it.created_at ?: "" } }
    val lastCardValue = remember(lastMessage) {
        lastMessage?.card_value ?: 0 }

    var enabledForProgress = remember { mutableStateOf(false) }

    fun loadMessages() {
        coroutineScope.launch {
            repository.loadMessages(
                onSuccess = { loadedMessages ->
                    messages = loadedMessages
                    isLoading.value = false
                    error.value = ""
                },
                onError = { errorMessage ->
                    error.value = errorMessage
                    isLoading.value = false
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            val connectivityStatus = NetworkUtils.isNetworkAvailable(context)
            mainViewModel.updateNetworkStatus(connectivityStatus)
            delay(5000)
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            loadMessages()
        }
    }

    val timerEnabled by mainViewModel.timerEnabled.collectAsState()

    LaunchedEffect(key1 = null) {
        if (timerEnabled){ mainViewModel.timerEnabledChange() }
    }

    if (!isOnline){
        IsNotOnlineDialog(context = context)
    } else {
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 64.dp, top = 64.dp)
        ) {

            ChatCards(
                userName = userName.value,
                repository = repository
            )

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth().padding(start = 64.dp)
            ) {
                GameBigCard(
                    image = bigCardImage(lastCardValue = lastCardValue),
                    text = lastCardValue.toString()
                )
                IconButton(onClick = { informationAboutGame.value = true }) {
                    Icon(
                        Icons.Rounded.Info,
                        contentDescription = "info about game",
                        tint = Color(0xFF9678B6),
                        modifier = Modifier.size(64.dp)
                    )
                }
            }

            GameCardBox(
                sliderPosition = sliderPosition,
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
                userMoneyValue = userMoneyValue,
                userGenerationLevel = userGenerationLevel,
                dataStoreManager = dataStoreManager,
                lastCardValue = lastCardValue.toString(),
                userName = userName,
                coroutineScope = coroutineScope,
                isSending = isSending,
                messageText = messageText,
                error = error,
                enabledForProgress = enabledForProgress,
                secondLastCardValue = secondLastCardValue.toString(),
                thirdLastCardValue = thirdLastCardValue.toString(),
                repository = repository
            )
            CustomSlider(sliderPosition = sliderPosition)
        }
    }
    if (informationAboutGame.value){
        MainInformation(
            informationEnabled = informationAboutGame,
            dialogText = R.string.game_rules,
            dialogImage = R.drawable.income_card_game,
            navController = navController
        )
    }
    BackHandler {
        navController.navigate(Screens.MainIncome.route)
    }
}