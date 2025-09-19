package igor.second.spaceapp.appwindows.cardSearching.locationCard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardGeneration.generator.buttons.addCardValue

@Composable
fun OnTargetUser(
    modifier: Modifier = Modifier,
    dataStoreManager: DataStoreManager,
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
    navController: NavController
) {
    var userSearchingValue = remember { mutableIntStateOf((161..320).random()) }

    // Определяем редкость
    val rarity = when (userSearchingValue.intValue) {
        in 161..240 -> "GOLD"
        else -> "DIAMOND"
    }

    val rarityColor = when (rarity) {
        "GOLD" -> Color(0xFFFFD700)
        else -> Color(0xFFB9F2FF)
    }

    val image = when (userSearchingValue.intValue) {
        in 161..170 -> R.drawable.gold_1
        in 171..180 -> R.drawable.gold_2
        in 181..190 -> R.drawable.gold_3
        in 191..200 -> R.drawable.gold_4
        in 201..210 -> R.drawable.gold_5
        in 211..220 -> R.drawable.gold_6
        in 221..230 -> R.drawable.gold_7
        in 231..240 -> R.drawable.gold_8
        in 241..250 -> R.drawable.diamond_1
        in 251..260 -> R.drawable.diamond_2
        in 261..270 -> R.drawable.diamond_3
        in 271..280 -> R.drawable.diamond_4
        in 281..290 -> R.drawable.diamond_5
        in 291..300 -> R.drawable.diamond_6
        in 301..310 -> R.drawable.diamond_7
        else -> R.drawable.diamond_8
    }

    var animationPlayed by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
                        MaterialTheme.colorScheme.background
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Заголовок
            AnimatedVisibility(
                visible = animationPlayed,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.on_target),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Индикатор редкости
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = rarityColor.copy(alpha = 0.2f)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = when (rarity) {
                                "GOLD" -> "ЗОЛОТАЯ НАХОДКА"
                                else -> "АЛМАЗНАЯ НАХОДКА"
                            },
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = rarityColor
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            // Картинка находки
            AnimatedVisibility(
                visible = animationPlayed,
                enter = scaleIn() + fadeIn(animationSpec = tween(1000)),
                modifier = Modifier.weight(2f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.6f)
                        .shadow(
                            elevation = 16.dp,
                            shape = RoundedCornerShape(32.dp),
                            spotColor = rarityColor.copy(alpha = 0.5f)
                        ),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        rarityColor.copy(alpha = 0.1f),
                                        Color.Transparent
                                    )
                                )
                            )
                    ) {
                        Image(
                            painter = painterResource(image),
                            contentDescription = "searching prize",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    if (animationPlayed) {
                                        rotationZ = 0f
                                    }
                                }
                        )
                    }
                }
            }

            // Кнопки
            AnimatedVisibility(
                visible = animationPlayed,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Кнопка "В меню"
                    OutlinedButton(
                        onClick = {
                            addCardValue(
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
                                dataStoreManager = dataStoreManager,
                                userGenerationLevel = userGenerationLevel,
                                userName = userName,
                                generationValue = userSearchingValue
                            )
                            navController.navigate(Screens.MainIncome.route)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        border = BorderStroke(
                            2.dp,
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.on_menu),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    // Кнопка "Искать снова"
                    Button(
                        onClick = {
                            addCardValue(
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
                                dataStoreManager = dataStoreManager,
                                userGenerationLevel = userGenerationLevel,
                                userName = userName,
                                generationValue = userSearchingValue
                            )
                            navController.navigate(Screens.MainSearching.route)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 4.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.search_again),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }
    }
}