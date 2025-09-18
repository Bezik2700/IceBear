package igor.second.spaceapp.appwindows.cardStartScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardStartScreen.dialog.UserInfoDialog

@Composable
fun StartGameProcess(
    navController: NavController,
    dialogShowValue: MutableState<Boolean>,
    dataStoreManager: DataStoreManager,
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
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
    userName: MutableState<String>
){

    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .clip(CircleShape)
            .clickable(onClick = {
                if (userName.value != "") {
                    navController.navigate(Screens.MainConnection.route)
                } else {
                    dialogShowValue.value = true
                }
            })
    ) {
        Box(modifier = Modifier){
            Image(
                painterResource(R.drawable.income_card_game),
                contentDescription = "game fon",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                val infiniteTransition = rememberInfiniteTransition()
                val scale by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.5f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1000, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                val alpha by infiniteTransition.animateFloat(
                    initialValue = 0.8f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1500, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Text(
                        text = stringResource(R.string.game),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF58AB),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                this.alpha = alpha
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
    if (dialogShowValue.value){
        UserInfoDialog(
            dialogShowValue = dialogShowValue,
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
            userName = userName,
            dataStoreManager = dataStoreManager,
            userMoneyValue = userMoneyValue,
            userGenerationLevel = userGenerationLevel
        )
    }
}