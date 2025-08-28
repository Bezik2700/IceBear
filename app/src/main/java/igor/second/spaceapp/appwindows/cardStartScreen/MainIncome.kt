package igor.second.spaceapp.appwindows.cardStartScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.Screens

@Composable
fun MainIncome(
    navController: NavController,
    modifier: Modifier = Modifier
){
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
            ) {

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
                    name = R.string.shopping_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 0.5f
                )
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainGeneration.route)},
                    name = R.string.generation_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 1f
                )
            }
            Row {
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainCamera.route)},
                    name = R.string.find_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 0.5f
                )
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainChange.route)},
                    name = R.string.change_screen,
                    image = R.drawable.ic_launcher_background,
                    cardWidth = 1f
                )
            }
        }
    }
}

