package igor.second.spaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.NavigationActivity
import igor.second.spaceapp.ui.theme.SpaceAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStoreManager = DataStoreManager(this)

        setContent {
            SpaceAppTheme {

                // data values
                var userGenerationLevel = remember { mutableIntStateOf(1) }
                var userMoneyValue = remember { mutableIntStateOf(0) }

                var bronzeValue1 = remember { mutableIntStateOf(0) }
                var bronzeValue2 = remember { mutableIntStateOf(0) }
                var bronzeValue3 = remember { mutableIntStateOf(0) }
                var bronzeValue4 = remember { mutableIntStateOf(0) }
                var bronzeValue5 = remember { mutableIntStateOf(0) }
                var bronzeValue6 = remember { mutableIntStateOf(0) }
                var bronzeValue7 = remember { mutableIntStateOf(0) }
                var bronzeValue8 = remember { mutableIntStateOf(0) }

                var silverValue1 = remember { mutableIntStateOf(0) }
                var silverValue2 = remember { mutableIntStateOf(0) }
                var silverValue3 = remember { mutableIntStateOf(0) }
                var silverValue4 = remember { mutableIntStateOf(0) }
                var silverValue5 = remember { mutableIntStateOf(0) }
                var silverValue6 = remember { mutableIntStateOf(0) }
                var silverValue7 = remember { mutableIntStateOf(0) }
                var silverValue8 = remember { mutableIntStateOf(0) }

                var goldValue1 = remember { mutableIntStateOf(0) }
                var goldValue2 = remember { mutableIntStateOf(0) }
                var goldValue3 = remember { mutableIntStateOf(0) }
                var goldValue4 = remember { mutableIntStateOf(0) }
                var goldValue5 = remember { mutableIntStateOf(0) }
                var goldValue6 = remember { mutableIntStateOf(0) }
                var goldValue7 = remember { mutableIntStateOf(0) }
                var goldValue8 = remember { mutableIntStateOf(0) }

                var diamondValue1 = remember { mutableIntStateOf(0) }
                var diamondValue2 = remember { mutableIntStateOf(0) }
                var diamondValue3 = remember { mutableIntStateOf(0) }
                var diamondValue4 = remember { mutableIntStateOf(0) }
                var diamondValue5 = remember { mutableIntStateOf(0) }
                var diamondValue6 = remember { mutableIntStateOf(0) }
                var diamondValue7 = remember { mutableIntStateOf(0) }
                var diamondValue8 = remember { mutableIntStateOf(0) }

                var platinumValue1 = remember { mutableIntStateOf(0) }
                var platinumValue2 = remember { mutableIntStateOf(0) }
                var platinumValue3 = remember { mutableIntStateOf(0) }
                var platinumValue4 = remember { mutableIntStateOf(0) }
                var platinumValue5 = remember { mutableIntStateOf(0) }
                var platinumValue6 = remember { mutableIntStateOf(0) }
                var platinumValue7 = remember { mutableIntStateOf(0) }
                var platinumValue8 = remember { mutableIntStateOf(0) }

                var epicValue1 = remember { mutableIntStateOf(0) }
                var epicValue2 = remember { mutableIntStateOf(0) }
                var epicValue3 = remember { mutableIntStateOf(0) }
                var epicValue4 = remember { mutableIntStateOf(0) }
                var epicValue5 = remember { mutableIntStateOf(0) }
                var epicValue6 = remember { mutableIntStateOf(0) }
                var epicValue7 = remember { mutableIntStateOf(0) }
                var epicValue8 = remember { mutableIntStateOf(0) }

                LaunchedEffect(true) {
                    dataStoreManager.getSettings().collect { settings ->
                        userGenerationLevel.intValue = settings.userGenerationLevel
                        userMoneyValue.intValue = settings.userMoneyValue
                        bronzeValue1.intValue = settings.bronzeValue1
                        bronzeValue2.intValue = settings.bronzeValue2
                        bronzeValue3.intValue = settings.bronzeValue3
                        bronzeValue4.intValue = settings.bronzeValue4
                        bronzeValue5.intValue = settings.bronzeValue5
                        bronzeValue6.intValue = settings.bronzeValue6
                        bronzeValue7.intValue = settings.bronzeValue7
                        bronzeValue8.intValue = settings.bronzeValue8
                        silverValue1.intValue = settings.silverValue1
                        silverValue2.intValue = settings.silverValue2
                        silverValue3.intValue = settings.silverValue3
                        silverValue4.intValue = settings.silverValue4
                        silverValue5.intValue = settings.silverValue5
                        silverValue6.intValue = settings.silverValue6
                        silverValue7.intValue = settings.silverValue7
                        silverValue8.intValue = settings.silverValue8
                        goldValue1.intValue = settings.goldValue1
                        goldValue2.intValue = settings.goldValue2
                        goldValue3.intValue = settings.goldValue3
                        goldValue4.intValue = settings.goldValue4
                        goldValue5.intValue = settings.goldValue5
                        goldValue6.intValue = settings.goldValue6
                        goldValue7.intValue = settings.goldValue7
                        goldValue8.intValue = settings.goldValue8
                        diamondValue1.intValue = settings.diamondValue1
                        diamondValue2.intValue = settings.diamondValue2
                        diamondValue3.intValue = settings.diamondValue3
                        diamondValue4.intValue = settings.diamondValue4
                        diamondValue5.intValue = settings.diamondValue5
                        diamondValue6.intValue = settings.diamondValue6
                        diamondValue7.intValue = settings.diamondValue7
                        diamondValue8.intValue = settings.diamondValue8
                        platinumValue1.intValue = settings.platinumValue1
                        platinumValue2.intValue = settings.platinumValue2
                        platinumValue3.intValue = settings.platinumValue3
                        platinumValue4.intValue = settings.platinumValue4
                        platinumValue5.intValue = settings.platinumValue5
                        platinumValue6.intValue = settings.platinumValue6
                        platinumValue7.intValue = settings.platinumValue7
                        platinumValue8.intValue = settings.platinumValue8
                        epicValue1.intValue = settings.epicValue1
                        epicValue2.intValue = settings.epicValue2
                        epicValue3.intValue = settings.epicValue3
                        epicValue4.intValue = settings.epicValue4
                        epicValue5.intValue = settings.epicValue5
                        epicValue6.intValue = settings.epicValue6
                        epicValue7.intValue = settings.epicValue7
                        epicValue8.intValue = settings.epicValue8
                    }
                }
                NavigationActivity(
                    dataStoreManager = dataStoreManager,
                    userMoneyValue = userMoneyValue,
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
                    navController = rememberNavController(),
                    userGenerationLevel = userGenerationLevel
                )
            }
        }
    }
}



