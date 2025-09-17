package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.chatcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.second.spaceapp.R
import igor.second.spaceapp.appwindows.gameProcess.settings.Message

@Composable
fun MessageUI(message: Message, isMyMessage: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, start = 8.dp, end = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isMyMessage) MaterialTheme.colorScheme.surfaceTint
            else MaterialTheme.colorScheme.surface
        )
    ) {
        if (isMyMessage){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.user),
                    fontSize = 12.sp
                )
                Text(
                    text = " ${message.sender_name} ",
                    fontSize = 18.sp
                )
                Text(
                    text = "${message.content} ",
                    fontSize = 12.sp
                )
                Text(
                    text = "(${message.card_value})",
                    fontSize = 18.sp
                )
            }
            HorizontalDivider()
        } else {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.user),
                    fontSize = 12.sp
                )
                Text(
                    text = " ${message.sender_name} ",
                    fontSize = 18.sp
                )
                Text(
                    text = "${message.content} ",
                    fontSize = 12.sp
                )
                Text(
                    text = "(${message.card_value})",
                    fontSize = 18.sp
                )
            }
            HorizontalDivider()
        }
    }
}