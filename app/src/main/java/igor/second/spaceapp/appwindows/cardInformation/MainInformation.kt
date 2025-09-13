package igor.second.spaceapp.appwindows.cardInformation

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import igor.second.spaceapp.appwindows.Screens

@Composable
fun MainInformation(
    informationEnabled: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    @StringRes dialogText: Int,
    @DrawableRes dialogImage: Int,
    navController: NavController
){
    Dialog(onDismissRequest = { informationEnabled.value = false }) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(onClick = {informationEnabled.value = false}) {
                    Text("close")
                }
                Image(
                    painterResource(dialogImage),
                    contentDescription = "image",
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = stringResource(dialogText)
                )
            }
        }
    }
    BackHandler {
        navController.navigate(Screens.MainIncome.route)
    }
}