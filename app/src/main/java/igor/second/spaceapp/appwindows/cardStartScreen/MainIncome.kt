package igor.second.spaceapp.appwindows.cardStartScreen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import igor.second.spaceapp.R
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardInformation.MainInformation

@Composable
fun MainIncome(
    navController: NavController,
    modifier: Modifier = Modifier
){

    var informationValue by remember { mutableIntStateOf(0) }
    var informationEnabled = remember { mutableStateOf(false) }

    var showExitDialog by remember { mutableStateOf(false) }
    var backPressTime by remember { mutableLongStateOf(0L) }

    val context = LocalContext.current

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 64.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Card (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
                    .clickable(onClick = {
                            navController.navigate(Screens.MainConnection.route)
                        }
                    )
            ) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("start game process")
                }
            }
        }
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth().fillMaxHeight(0.5f)
            ) {
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainShop.route)},
                    onClickInformation = {
                        informationValue = 1
                        informationEnabled.value = true
                                         },
                    name = R.string.shopping_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 0.5f
                )
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainGeneration.route)},
                    onClickInformation = {
                        informationValue = 2
                        informationEnabled.value = true
                                         },
                    name = R.string.generation_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 1f
                )
            }
            Row {
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainSearching.route)},
                    onClickInformation = {
                        informationValue = 3
                        informationEnabled.value = true
                                         },
                    name = R.string.find_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 0.5f
                )
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainCollection.route)},
                    onClickInformation = {
                        informationValue = 4
                        informationEnabled.value = true
                                         },
                    name = R.string.collection_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 1f
                )
            }
        }
    }
    if (informationEnabled.value){
        MainInformation(
            informationEnabled = informationEnabled,
            dialogText = when (informationValue){
                1 -> R.string.bronze1
                2 -> R.string.bronze2
                3 -> R.string.bronze3
                else -> R.string.bronze4
            },
            dialogImage = when (informationValue){
                1 -> R.drawable.bronze_1
                2 -> R.drawable.bronze_2
                3 -> R.drawable.bronze_3
                else -> R.drawable.bronze_4
            },
            navController = navController
        )
    }

    if (showExitDialog) {
        ExitDialogGame(
            onConfirm = { (context as? Activity)?.finish() },
            onDismiss = { showExitDialog = false }
        )
    }

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressTime > 2000) {
            backPressTime = currentTime
            Toast.makeText(context, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show()
        } else {
            showExitDialog = true
        }
    }
}
