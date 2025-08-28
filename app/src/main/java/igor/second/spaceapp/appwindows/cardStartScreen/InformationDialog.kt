package igor.second.spaceapp.appwindows.cardStartScreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun InformationDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes dialogText: Int,
    @DrawableRes dialogImage: Int,
){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painterResource(dialogImage),
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.fillMaxSize(),
                    contentDescription = "income card fon"
                )
                Text(
                    text = stringResource(dialogText),
                    modifier = modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}