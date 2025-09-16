package igor.second.spaceapp.appwindows.cardStartScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import igor.second.spaceapp.appwindows.Screens

@Composable
fun StartGameProcess(navController: NavController){
    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
            .clickable(onClick = {
                navController.navigate(Screens.MainConnection.route) }
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