package igor.second.spaceapp.appwindows.cardStartScreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun IncomeCardValue(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onClickInformation: () -> Unit,
    cardWidth: Float,
    @StringRes name: Int,
    @DrawableRes image: Int
){
    Card (
        modifier = modifier
            .fillMaxWidth(cardWidth)
            .clickable(onClick = {onClick.invoke()})
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
    ) {
        Box(modifier){
            Image(
                painterResource(image),
                contentScale = ContentScale.FillBounds,
                modifier = modifier.fillMaxSize(),
                contentDescription = "income card fon"
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    onClickInformation.invoke()
                }) {
                    Icon(
                        Icons.Rounded.Info,
                        contentDescription = null
                    )
                }
            }
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                Text(stringResource(name))
            }
        }
    }
}